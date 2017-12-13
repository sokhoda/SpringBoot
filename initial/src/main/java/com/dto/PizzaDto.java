package com.dto;

import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDto extends ResourceSupport implements Serializable {

    private Long pizzaId;
    private String name;
    private String type;
    private Double price;
}
