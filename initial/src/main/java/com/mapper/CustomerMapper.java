package com.mapper;

import com.businessdomain.Customer;
import com.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerToCustomerEntity(CustomerDto customerDto);

    CustomerDto customerEntityToCustomer(Customer customer);
}
