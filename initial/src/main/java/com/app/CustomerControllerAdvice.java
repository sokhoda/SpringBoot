package com.app;

import com.businessdomain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import com.service.CustomerService;

@ControllerAdvice(assignableTypes = {CustomerController.class})
public class CustomerControllerAdvice {
    @Autowired
    CustomerService customerService;

    @ModelAttribute(name = "customer")
    public Customer loadCustomer(@RequestParam(name = "customerId", required = false) Customer customer) {
        System.out.println("ControllerAdvice: " + customer);

        return customer;
    }

}
