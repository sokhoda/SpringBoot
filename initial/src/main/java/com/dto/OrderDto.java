package com.dto;

import com.businessdomain.Cheque;
import com.businessdomain.Customer;
import com.businessdomain.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends ResourceSupport implements Serializable {

    private Long orderId;
    private Cheque cheque;
    private Customer customer;
    private Map<Pizza, Integer> pizzaMap;
}
