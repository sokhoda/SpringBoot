package com.repository;

import com.businessdomain.Customer;
import com.businessdomain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByCustomer(Customer customer);

}
