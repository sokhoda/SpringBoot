package com.dto;

import com.businessdomain.Address;
import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto extends ResourceSupport implements Serializable {
    private Long customerId;
    @NotNull
    private String name;
    @Size(min = 10)
    private String email;
    private Address address;
}
