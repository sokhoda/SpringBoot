package repository;

import businessdomain.Customer;
import businessdomain.Orders;
import org.springframework.data.repository.CrudRepository;
import pizzaservice.states.State;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends CrudRepository<Orders, Long> {
    Orders find(Long id);

    List<Orders> findByCustomer(Customer customer);

    List<Orders> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    List<Orders> findByCustomerByState(Customer customer, State state);

    List<Orders> findByDateBetweenByState(LocalDateTime fromDate, LocalDateTime toDate, State state);

    List<Orders> findByDateBetweenByStateByCustomer(
            LocalDateTime fromDate, LocalDateTime toDate, State state, Customer customer);
}
