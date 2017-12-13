package com.assemblers;

import com.businessdomain.Customer;
import com.dto.CustomerDto;
import com.google.common.collect.Lists;
import com.mapper.CustomerMapper;
import com.resource.CustomerResource;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerAssembler extends JaxRsResourceAssemblerSupport<Customer, CustomerDto> {

    CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    public CustomerAssembler() {
        super(CustomerResource.class, CustomerDto.class);
    }

    @Override
    public CustomerDto toResource(Customer entity) {
        CustomerDto result = customerMapper.customerEntityToCustomer(entity);
        CustomerDto customerDto = createResourceWithId(entity.getCustomerId(), entity);
        result.add(customerDto.getLinks());
        return result;
    }

    @Override
    public List<CustomerDto> toResources(Iterable<? extends Customer> entities) {
        List<CustomerDto> customerDtoList = Lists.newArrayList();
        entities.forEach(entity -> customerDtoList.add(toResource(entity)));
        return customerDtoList;
    }
}
