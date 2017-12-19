package com.businessdomain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Pizza implements Serializable {
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
}
