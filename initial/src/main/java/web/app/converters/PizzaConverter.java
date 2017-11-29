package web.app.converters;

import businessdomain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import pizzaservice.PizzaService;

import java.util.Objects;

public class PizzaConverter implements Converter<String, Pizza> {
    @Autowired
    PizzaService pizzaService;

    @Override
    public Pizza convert(String pizzaIdStr) {
        System.out.println("Convert " + pizzaIdStr);
        Pizza result = new Pizza();
        if (!StringUtils.isEmpty(pizzaIdStr)) {
            Long pizzaId = Long.valueOf(pizzaIdStr);
            result = Objects.nonNull(pizzaId) ? pizzaService.find(pizzaId) : null;
        }
        return result;
    }
}
