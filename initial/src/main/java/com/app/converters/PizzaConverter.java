package com.app.converters;

import com.businessdomain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.service.PizzaService;

import java.util.Objects;

@Component
public class PizzaConverter implements Converter<String, Pizza> {
    @Autowired
    private PizzaService pizzaService;

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
