package com.service;

import com.businessdomain.Customer;
import org.springframework.validation.BindingResult;

public interface CustomerValidationService  {
    boolean isNotValid(Customer customer, BindingResult bindingResult, Class<?> group);
}