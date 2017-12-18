package com.service;

import com.app.converters.PizzaConverter;
import com.businessdomain.Cheque;
import com.businessdomain.Customer;
import com.businessdomain.Orders;
import com.businessdomain.Pizza;
import com.businessdomain.states.OrderStateCycle;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private static final String USER = "user";

    @Inject
    private PizzaService pizzaService = null;
    @Autowired
    private OrderRepository orderRepository = null;
    @Inject
    private ChequeService chequeService;

    public OrderServiceImpl() {
    }

    public OrderServiceImpl(PizzaService pizzaService,
                            OrderRepository orderRepository) {
        this.pizzaService = pizzaService;
        this.orderRepository = orderRepository;
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
        Orders newOrder =  new Orders();
        newOrder.setCustomer(customer);
        newOrder.setPizzaMap(pizzaMap);
        newOrder.setOrderStateCycle(new OrderStateCycle());

        return save(newOrder);
    }

    @Override
    public Orders placeNewOrder(Customer customer, Map<Pizza, Integer> orderedPizzas) {
        Orders order = new Orders();
        Cheque cheque = chequeService.placeCheque(orderedPizzas);
        order.setCheque(cheque);
        order.setCustomer(customer);
        order.setPizzaMap(orderedPizzas);
        order.setOrderStateCycle(new OrderStateCycle());

        return save(order);
    }

    @Override
    public void buildOrder(String orderedPizzaIds, Customer customer, PizzaConverter pizzaConverter) {
        Map<Pizza, Integer> orderedPizzas = createOrderedPizzaMap(orderedPizzaIds, pizzaConverter);
        Orders order = placeNewOrder(customer, orderedPizzas);
    }

    private Map<Pizza, Integer> createOrderedPizzaMap(String orderedPizzaIds, PizzaConverter pizzaConverter) {
        Map<Pizza, Integer> orderedPizzas = new HashMap<>();
        Arrays.stream(orderedPizzaIds.split(";"))
                .forEach(orderItem -> populateOrderedPizza(orderedPizzas, orderItem, pizzaConverter));
        return orderedPizzas;
    }

    private void populateOrderedPizza(Map<Pizza, Integer> orderedPizzas, String idQuantityPair, PizzaConverter pizzaConverter) {
        Assert.hasText(idQuantityPair);
        String[] orderItem = idQuantityPair.split(",");
        Integer quantity = Integer.valueOf(orderItem[1]);
        if (quantity > 0) {
            Pizza pizza = pizzaConverter.convert(orderItem[0]);
            orderedPizzas.put(pizza, quantity);
        }
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
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return resultOrder;
    }

    @Override
    public Orders find(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public List<Orders> findByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    @Override
    public Orders save(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public String toString() {
        return "OrderServiceImpl{" +
                "orderRepository=" + orderRepository +
                ", pizzaService=" + pizzaService +
                '}';
    }

    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
