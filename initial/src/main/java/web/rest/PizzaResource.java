package web.rest;

import businessdomain.PizzaType;
import org.springframework.hateoas.ResourceSupport;


public class PizzaResource extends ResourceSupport {
    private Long pizzaId;

    private String name;

    private Double price;

    private PizzaType type;

    public PizzaResource() {
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Long getPizzaId() {
        return pizzaId;
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
