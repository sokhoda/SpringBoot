package com.app.utils.discount;

import com.businessdomain.Cheque;
import com.businessdomain.DiscountRecord;
import com.businessdomain.Orders;
import org.springframework.stereotype.Component;

@Component
public class ByQuantityDiscountHandler implements DiscountHandler {
    private DiscountHandler next;

    @Override
    public void setNext(DiscountHandler handler) {
        next = handler;
    }

    @Override
    public void handleDiscount(Orders order, Cheque cheque) {
        if (order.getPizzaMap().size() > DISCOUNT_THRESHOLD) {
            Double discountSum = order.getTheMostExpensivePizza().getPrice() *
                    DISCOUNT_MOST_EXPENS_PIZZA_PERCENTAGE / 100.;
            String discountName = this.getClass().getSimpleName() + ", order size > " + DISCOUNT_THRESHOLD;
            cheque.getDiscountList().add(new DiscountRecord(discountName, discountSum, cheque));
        }
        next.handleDiscount(order, cheque);
    }
}
