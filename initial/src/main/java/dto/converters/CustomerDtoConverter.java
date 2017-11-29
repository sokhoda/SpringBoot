package dto.converters;

import businessdomain.Customer;
import dto.CustomerDto;

import java.util.Objects;

public class CustomerDtoConverter {

    public static Customer toCustomerEntity(CustomerDto customerDto) {
        if (Objects.isNull(customerDto)) {
            return null;
        }
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }

    public static CustomerDto toCustomerDto(Customer customer) {
        if (Objects.isNull(customer)) {
            return null;
        }
        return CustomerDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }
}
