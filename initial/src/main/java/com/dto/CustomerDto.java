package com.dto;

import com.businessdomain.Address;
import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto extends ResourceSupport implements Serializable {
    private Long customerId;
    private String name;
    private String email;
    private Address address;
}
