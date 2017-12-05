package web.app.dto;

import businessdomain.DomainHelper;
import businessdomain.Pizza;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = DomainHelper.PIZZALIST)
public class PizzaList implements Serializable {

    @XmlElement(name = DomainHelper.PIZZA, type = Pizza.class)
    private List<Pizza> pizzaList;

    public PizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public PizzaList() {
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }
}
