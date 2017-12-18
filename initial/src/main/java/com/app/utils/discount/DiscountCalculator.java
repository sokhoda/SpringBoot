package com.app.utils.discount;

import com.businessdomain.Cheque;
import com.businessdomain.Orders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DiscountCalculator {
    private DiscountHandler prevHandler;
    private DiscountHandler firstHandler;

    @PostConstruct
    public void init(){
        addHandler(new ByQuantityDiscountHandler());
        addHandler(new GeneralLoyaltyCardDiscountHandler());
    }

    public DiscountCalculator() {
    }

    public void addHandler(DiscountHandler handler) {
        if (prevHandler != null) {
            prevHandler.setNext(handler);
        }
        else {
            firstHandler = handler;
        }
        prevHandler = handler;
    }

    public Cheque handleDiscount(Orders order, Cheque cheque){
        Cheque newCheque = new Cheque(cheque);
        firstHandler.handleDiscount(order, newCheque);
        return newCheque;
    }
}
