package pizzaservice;

import businessdomain.Cheque;
import businessdomain.Customer;
import businessdomain.Orders;
import businessdomain.Pizza;
import infrastructure.event.handling.events.OrderCreatedEvent;
import infrastructure.event.handling.publishers.OrderCreatedEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import pizzaservice.cheque.ChequeProducer;
import pizzaservice.states.OrderStateCycle;
import pizzaservice.states.State;
import repository.OrderRepository;
import web.app.converters.PizzaConverter;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service("orderService")
public class SimpleOrderService implements OrderService {
    private static final String USER = "user";
    private static final String ATTACHMENT_FILENAME = "orderSummary.xlsx";

    @Inject
    private PizzaService pizzaService = null;
    @Autowired
    @Qualifier(value = "orderRepository")
    private OrderRepository orderRepo = null;
    @Inject
    private ChequeProducer chequeProducer;
    @Inject
    private OrderStateCycle orderStateCycle;
    @Inject
    private OrderCreatedEventPublisher orderCreatedEventPublisher;
    @Inject
    private CustomMailService customMailService;


    @Value("#{properties['order.created.message']}")
    private String orderCreatedMessage;

    public SimpleOrderService() {
    }

    public SimpleOrderService(PizzaService pizzaService,
                              OrderRepository orderRepo) {
        this.pizzaService = pizzaService;
        this.orderRepo = orderRepo;
    }

    @Override
    public void doCreateOrder(Model model, HttpSession session) {
        List<Pizza> pizzas = pizzaService.findAll();
        List<Long> pizzaIds = pizzas.stream().map(Pizza::getPizzaId).collect(Collectors.toList());
        model.addAttribute("pizzaIds", pizzaIds.toString());
        model.addAttribute("pizzas", pizzas);
        model.addAttribute(USER, session.getAttribute(USER));
    }

    @Override
    public void addTotalSumToCustomerLCard(Orders order) {
        if (order != null) {
            order.addTotalSumToCustomerLCard();
        }
    }

    @Transactional
    @Override
    public Orders placeNewOrder(Customer customer, Long... pizzasID) {
        Map<Pizza, Integer> pizzaMap = new HashMap<>();
        Pizza pizza;
        for (Long id : pizzasID) {
            pizza = pizzaService.find(id);
            if (pizza != null) {
                pizzaMap.put(pizza, 1);
            }
        }
        Orders newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzaMap(pizzaMap);
        newOrder.setOrderStateCycle(createNewOrderStateCycle());

        return save(newOrder);
    }

    @Override
    public Orders placeNewOrder(Customer customer, Map<Pizza, Integer> orderedPizzas) {
        Orders order = createNewOrder();
        Cheque cheque = chequeProducer.placeCheque(orderedPizzas);
        order.setCheque(cheque);
        order.setCustomer(customer);
        order.setPizzaMap(orderedPizzas);
        order.setOrderStateCycle(createNewOrderStateCycle());

        return save(order);
    }

    @Override
    public void buildOrder(String orderedPizzaIds, Customer customer, PizzaConverter pizzaConverter) {
        Map<Pizza, Integer> orderedPizzas = createOrderedPizzaMap(orderedPizzaIds, pizzaConverter);
        Orders order = placeNewOrder(customer, orderedPizzas);
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(this, order, orderCreatedMessage);
        orderCreatedEventPublisher.doPublishEvent(orderCreatedEvent);
        File attachment = null;
        customMailService.sendMail(attachment, ATTACHMENT_FILENAME, order);
    }

    private Map<Pizza, Integer> createOrderedPizzaMap(String orderedPizzaIds, PizzaConverter pizzaConverter) {
        Map<Pizza, Integer> orderedPizzas = new HashMap<>();
        Arrays.stream(orderedPizzaIds.split(";"))
                .forEach(orderItem -> populateOrderedPizza(orderedPizzas, orderItem, pizzaConverter));
        return orderedPizzas;
    }

    private void populateOrderedPizza(Map<Pizza, Integer> orderedPizzas, String idQuantityPair, PizzaConverter pizzaConverter) {
        String[] orderItem = idQuantityPair.split(",");
        Integer quantity = Integer.valueOf(orderItem[1]);
        if (quantity > 0) {
            Pizza pizza = pizzaConverter.convert(orderItem[0]);
            orderedPizzas.put(pizza, quantity);
        }
    }


    Orders createNewOrder() {
        throw new IllegalStateException("Container couldn't create Proxy");
    }

    OrderStateCycle createNewOrderStateCycle() {
        throw new IllegalStateException("Container couldn't create Proxy");
    }

    @Transactional
    @Override
    public Orders addPizzas(final Orders order, final Long... idNoPair) {
        Orders resultOrder = null;
        Pizza currentPizza = null;
        if (order != null) {
            Map<Pizza, Integer> pizzaMap = order.getPizzaMap();
            try {
                for (int i = 0; i < idNoPair.length; i = i + 2) {
                    currentPizza = pizzaService.find(idNoPair[i]);
                    if (currentPizza != null) {
                        Integer curNumber = pizzaMap.get(currentPizza);
                        pizzaMap.put(currentPizza, idNoPair[i + 1].intValue()
                                + (curNumber == null ? 0 : curNumber));
                    }
                }
                resultOrder = save(order);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return resultOrder;
    }

    @Override
    public Orders find(Long id) {
        return orderRepo.find(id);
    }

    @Override
    public List<Orders> findByCustomer(Customer customer) {
        return orderRepo.findByCustomer(customer);
    }

    @Override
    public List<Orders> findAll() {
        return orderRepo.findAll();
    }

    @Override
    public List<Orders> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate){
        return  orderRepo.findByDateBetween(fromDate, toDate);
    }

    @Override
    public List<Orders> findByDateBetweenByState(LocalDateTime fromDate,
                                                 LocalDateTime toDate, State
                                                         state){
        return  orderRepo.findByDateBetweenByState(fromDate, toDate, state);
    }
    @Override
    public List<Orders> findByDateBetweenByStateByCustomer(LocalDateTime fromDate,
                                                           LocalDateTime toDate, State state,
                                                           Customer customer){
        return orderRepo.findByDateBetweenByStateByCustomer(fromDate, toDate, state,
                customer);
    }

    @Transactional
    @Override
    public Orders save(Orders order) {
        return orderRepo.save(order);
    }

    @Override
    public List<Orders> findByCustomerByState(Customer customer, State state){
        return orderRepo.findByCustomerByState(customer, state);
    }

    @Override
    public String toString() {
        return "SimpleOrderService{" +
                "orderRepo=" + orderRepo +
                ", pizzaService=" + pizzaService +
                '}';
    }


    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    public void setOrderRepo(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
}
