package com.app.converters;

import com.businessdomain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.service.CustomerService;

import java.util.Objects;

@Component
public class CustomerConverter implements Converter<String, Customer> {
    @Autowired
    private CustomerService customerService;

    @Override
    public Customer convert(String customerIdStr) {
        System.out.println("Convert " + customerIdStr);
        Customer result = new Customer();
        if (!StringUtils.isEmpty(customerIdStr)) {
            Long customerId = Long.valueOf(customerIdStr);
            result = Objects.nonNull(customerId) ? customerService.find(customerId) : null;
        }
        return result;
    }
}
