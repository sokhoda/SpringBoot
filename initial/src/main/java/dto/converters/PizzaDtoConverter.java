package dto.converters;

import businessdomain.Pizza;
import businessdomain.PizzaType;
import dto.PizzaDto;

import java.util.Objects;

public class PizzaDtoConverter {

    public static Pizza toPizzaEntity(PizzaDto pizzaDto) {
        if (Objects.isNull(pizzaDto)) {
            return null;
        }
        Pizza pizza = new Pizza();
        pizza.setName(pizzaDto.getName());
        pizza.setType(PizzaType.valueOf(pizzaDto.getType()));
        pizza.setPrice(pizzaDto.getPrice());
        return pizza;
    }

    public static PizzaDto toPizzaDto(Pizza pizza) {
        if (Objects.isNull(pizza)) {
            return null;
        }
        return PizzaDto.builder()
                .name(pizza.getName())
                .type(pizza.getType().name())
                .price(pizza.getPrice())
                .build();
    }
}
