package com.app.utils.discount;

import com.businessdomain.Cheque;
import com.businessdomain.DiscountRecord;
import com.businessdomain.LoyaltyCard;
import com.businessdomain.Orders;
import org.springframework.stereotype.Component;

@Component
public class GeneralLoyaltyCardDiscountHandler implements DiscountHandler {
    private DiscountHandler next;

    @Override
    public void setNext(DiscountHandler handler) {
        next.setNext(handler);
    }

    @Override
    public void handleDiscount(Orders order, Cheque cheque) {
        LoyaltyCard loyaltyCard = order.getCustomer().getLoyaltyCard();
        if (loyaltyCard != null) {
            Double discount1 = order.calcTotalSum() *
                    DISCOUNT_MAX_ORDER_SUM_PERCENTAGE / 100.;
            Double discount2 = loyaltyCard.getSum() * DISCOUNT_LOYALTY_CARD_SUM_PERCENTAGE / 100.;
            Double discountSum = discount2 > discount1 ? discount1 : discount2;
            String discountName = this.getClass().getSimpleName() + ", " +
                    DISCOUNT_LOYALTY_CARD_SUM_PERCENTAGE + "% discount of " +
                    "LoyaltyCard Sum";
            cheque.getDiscountList().add(new DiscountRecord(discountName, discountSum, cheque));
        }
    }
}
