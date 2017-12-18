package com.service;

import com.app.converters.PizzaConverter;
import com.businessdomain.Customer;
import com.businessdomain.Orders;
import com.businessdomain.Pizza;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
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

    Orders save(Orders order);
}
