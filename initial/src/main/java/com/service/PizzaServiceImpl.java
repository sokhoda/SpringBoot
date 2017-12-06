package com.service;

import com.businessdomain.Pizza;
import com.businessdomain.PizzaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.repository.PizzaRepository;

import java.util.List;

@Service("pizzaService")
public class PizzaServiceImpl implements PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public PizzaServiceImpl() {
    }

    public PizzaServiceImpl(PizzaRepository pizzaRepo) {
        this.pizzaRepository = pizzaRepo;
    }

    @Transactional
    @Override
    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public Pizza find(Long id) {
        return pizzaRepository.findOne(id);
    }

    @Override
    public List<Pizza> findAll() {
        return (List<Pizza>) pizzaRepository.findAll();
    }

    @Override
    public void remove(Pizza pizza) {
        Assert.notNull(pizza, "Pizza should not be null");
        pizzaRepository.delete(pizza);
    }

    @Override
    public List<Pizza> findByType(PizzaType type) {
        return pizzaRepository.findByType(type);
    }

    @Override
    public List<Pizza> findByName(String name) {
        return pizzaRepository.findByName(name);
    }

    public static Pizza getVegetarianPizza() {
        return new Pizza(null, "Vege", 123., PizzaType.VEGETERIAN);
    }

    public final Pizza finalMethod() {
        return new Pizza();
    }


    public Pizza privateMethodCaller() {
        return privateGetPizza(1L);
    }

    private Pizza privateGetPizza(Long id) {
        final Pizza pizza = new Pizza();
        pizza.setPizzaId(1L);
        return pizza;
    }
}
