package businessdomain;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Pizza.findByType", query =
                "SELECT p from Pizza p WHERE p.type = :type"),
        @NamedQuery(name = "Pizza.findByName", query =
                "SELECT p from Pizza p WHERE p.name = :name")
})
@XmlRootElement(name = DomainHelper.PIZZA)
public class Pizza extends ResourceSupport implements Serializable {
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

//    private Long companyID;

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

    @Override
    public String toString() {
        return "\nPizza{" +
                "pizzaId=" + pizzaId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (name != null ? !name.equals(pizza.name) : pizza.name != null)
            return false;
        if (price != null ? !price.equals(pizza.price) : pizza.price != null)
            return false;
        return type == pizza.type;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @PrePersist
    public void notifyMe() {
        System.out.println("PrePersist method invocation");
    }

    @PostPersist
    public void notifyMe1() {
        System.out.println("PostPersist method invocation");
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
