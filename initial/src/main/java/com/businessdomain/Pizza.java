package com.businessdomain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Pizza implements Serializable{
    @Id
    @TableGenerator(
            name = "pizzaGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "PIZZA_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pizzaGen")
    private Long pizzaId;

    private String name;

    private Double price;

    @Enumerated(EnumType.STRING)
    private PizzaType type;

    public Pizza(Long id, String name, Double price, PizzaType type) {
        this.pizzaId = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Pizza() {
    }

    public Pizza(Long id, String name) {
        this.pizzaId = id;
        this.name = name;
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
        this.type = type;
    }
}
