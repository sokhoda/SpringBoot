package com.service;

import com.businessdomain.Pizza;
import com.businessdomain.PizzaType;

import java.util.List;

public interface PizzaService {
    Pizza save(Pizza pizza);

    Pizza find(Long id);

    List<Pizza> findAll();

    List<Pizza> findByType(PizzaType type);

    List<Pizza> findByName(String name);

    void remove(Pizza pizza);

}
