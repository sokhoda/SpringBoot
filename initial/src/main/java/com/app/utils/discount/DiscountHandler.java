package com.app.utils.discount;

import com.businessdomain.Cheque;
import com.businessdomain.Orders;

public interface DiscountHandler {
    int DISCOUNT_THRESHOLD = 4;
    int DISCOUNT_MOST_EXPENS_PIZZA_PERCENTAGE = 30;
    int DISCOUNT_MAX_ORDER_SUM_PERCENTAGE = 30;
    int DISCOUNT_LOYALTY_CARD_SUM_PERCENTAGE = 10;

    void setNext(DiscountHandler handler);

    void handleDiscount(Orders order, Cheque cheque);
}
