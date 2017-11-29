package web.app.converters;

import businessdomain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import pizzaservice.CustomerService;

import java.util.Objects;

public class CustomerConverter implements Converter<String, Customer> {
    @Autowired
    CustomerService customerService;

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
