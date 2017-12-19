package com.businessdomain;

import com.businessdomain.states.OrderStateCycle;
import com.businessdomain.states.State;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "TB_ORDER")
@Getter
@Setter
@EqualsAndHashCode
@ToString
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
    private Long orderId;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "CHEQUE_ID")
    private Cheque cheque;

    @ManyToOne(cascade = CascadeType.PERSIST)
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
        this.orderId = id;
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
}
