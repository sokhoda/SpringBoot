package com.mapper;

import com.businessdomain.Pizza;
import com.dto.PizzaDto;
import org.mapstruct.Mapper;

@Mapper
public interface PizzaMapper {

    Pizza pizzaToPizzaEntity(PizzaDto pizzaDto);

    PizzaDto pizzaEntityToPizza(Pizza pizza);
}
