package pizzaservice;

import businessdomain.Customer;
import businessdomain.Orders;
import businessdomain.Pizza;
import org.springframework.ui.Model;
import pizzaservice.states.State;
import web.app.converters.PizzaConverter;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderService {
    void doCreateOrder(Model model, HttpSession session);

    void addTotalSumToCustomerLCard(Orders order);

    Orders placeNewOrder(Customer customer, Long... pizzasID);

    Orders placeNewOrder(Customer customer, Map<Pizza, Integer> orderedPizzas);

    void buildOrder(String orderedPizzaIds, Customer customer, PizzaConverter pizzaConverter);

    Orders addPizzas(Orders order, Long... idNoPair);

    Orders find(Long id);

    List<Orders> findAll();

    List<Orders> findByCustomer(Customer customer);

    List<Orders> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    List<Orders> findByDateBetweenByState(LocalDateTime fromDate,
                                          LocalDateTime toDate, State
                                                  state);

    List<Orders> findByDateBetweenByStateByCustomer(LocalDateTime fromDate,
                                                    LocalDateTime toDate, State state,
                                                    Customer customer);

    Orders save(Orders order);

    List<Orders> findByCustomerByState(Customer customer, State
            stateEn);
}
