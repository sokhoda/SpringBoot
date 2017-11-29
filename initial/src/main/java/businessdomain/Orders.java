package businessdomain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pizzaservice.states.OrderStateCycle;
import pizzaservice.states.State;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

@Component
@Scope("prototype")
@Entity
@NamedQueries({
        @NamedQuery(name = "Order.findByCustomer", query =
                "SELECT ord from Orders ord WHERE ord.customer = :customer"),
        @NamedQuery(name = "Order.findByDateBetween", query =
                "SELECT ord from Orders ord JOIN ord.cheque chq " +
                    "WHERE chq.date BETWEEN :fromDate AND :toDate"),
        @NamedQuery(name = "Order.findByDateBetweenByState", query =
                "SELECT ord from Orders ord " +
                        "WHERE ord.cheque.date BETWEEN :fromDate AND :toDate" +
                        " AND ord.orderStateCycle.curState = :state"),
        @NamedQuery(name = "Order.findByCustomerByState", query =
                "SELECT ord from Orders ord " +
                        "WHERE ord.orderStateCycle.curState = :state AND " +
                        "ord.customer = :customer"),
        @NamedQuery(name = "Order.findAll", query = "SELECT ord from Orders ord")

})
@Table(name = "TB_ORDER")
@Access(AccessType.FIELD)
@XmlRootElement(name = DomainHelper.ORDERS)
@JsonIgnoreProperties({"orderStateCycle","theMostExpensivePizza"})
public class Orders implements Serializable {
    @Id
    @TableGenerator(
            name = "orderGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "ORDER_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "orderGen")
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "CHEQUE_ID")
    private Cheque cheque;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CUST_ID")
    private Customer customer;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PIZZA_QUANT", joinColumns = @JoinColumn(name = "Ord_ID"))
    @MapKeyJoinColumn(name = "PIZZA_ID")
    @Column(name = "QUANTITY")
    private Map<Pizza, Integer> pizzaMap;

    @Autowired
    @Embedded
    private OrderStateCycle orderStateCycle;

    public Orders() {
    }

    public Orders(Long id, Customer customer, Map<Pizza, Integer> pizzaMap) {
        this.id = id;
        this.customer = customer;
        this.pizzaMap = pizzaMap;
    }

    public Orders(Customer customer, Map<Pizza, Integer> pizzaMap, OrderStateCycle orderStateCycle) {
        this.customer = customer;
        this.pizzaMap = pizzaMap;
        this.orderStateCycle = orderStateCycle;
    }

    public Orders(Customer customer, Map<Pizza, Integer> pizzaMap) {
        this.customer = customer;
        this.pizzaMap = pizzaMap;
    }

    public Double calcTotalSum() {
        Double sum = 0.;
        for (Pizza pizza : pizzaMap.keySet()) {
            sum += pizza.getPrice() * pizzaMap.get(pizza);
        }
        return sum;
    }

    public Pizza getTheMostExpensivePizza() {
        SortedSet<Map.Entry<Pizza, Integer>> pizzaSortedSet = new TreeSet<>(
                (o1, o2) -> {
                    return o2.getValue().compareTo(o1.getValue());
                });
        pizzaSortedSet.addAll(pizzaMap.entrySet());

        return pizzaSortedSet.first().getKey();
    }


    public int countPizzasQuantity() {
        return getPizzaMap().values().stream().reduce(0, Integer::sum);
    }

    public double calculateTotalprice() {
        return getPizzaMap().entrySet().stream()
                .map(this::calculateOrderEntryPrice)
                .reduce(0.0, Double::sum);
    }

    private double calculateOrderEntryPrice(Map.Entry<Pizza, Integer> entry) {
        return entry.getKey().getPrice() * entry.getValue();
    }

    public void addTotalSumToCustomerLCard() {
        LoyaltyCard loyaltyCard = customer.getLoyaltyCard();
        if (loyaltyCard != null) {
            loyaltyCard.setSum(loyaltyCard.getSum() + calcTotalSum());
        }
    }

    public State nextState() {
        return orderStateCycle.nextState();
    }

    public State previousState() {
        return orderStateCycle.previousState();
    }

    public void cancel() {
        orderStateCycle.setCurState(orderStateCycle.getCancelledSt());
    }

    @Override
    public String toString() {
        return "\nOrder{" +
                "id=" + id +
                ", cheque=" + cheque +
                ",\ncustomer=" + customer +
                ",\norderStateCycle=" + orderStateCycle +
                ", pizzaMap=" + pizzaMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders order = (Orders) o;

        if (cheque != null ? !cheque.equals(order.cheque) : order.cheque != null)
            return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null)
            return false;
        if (pizzaMap != null ? !pizzaMap.equals(order.pizzaMap) : order.pizzaMap != null)
            return false;
        return orderStateCycle != null ? orderStateCycle.equals(order.orderStateCycle) : order.orderStateCycle == null;

    }

    public Cheque getCheque() {
        return cheque;
    }

    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Pizza, Integer> getPizzaMap() {
        return pizzaMap;
    }

    public void setPizzaMap(Map<Pizza, Integer> pizzaMap) {
        this.pizzaMap = pizzaMap;
    }


    public void setOrderStateCycle(OrderStateCycle orderStateCycle) {
        this.orderStateCycle = orderStateCycle;
    }

    public OrderStateCycle getOrderStateCycle() {
        return orderStateCycle;
    }
}
