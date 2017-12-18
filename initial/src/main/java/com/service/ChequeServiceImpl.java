package com.service;

import com.app.utils.discount.DiscountCalculator;
import com.businessdomain.Cheque;
import com.businessdomain.Pizza;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service("chequeService")
public class ChequeServiceImpl implements ChequeService {
    private DiscountCalculator discountCalculator;


    public ChequeServiceImpl() {
    }

    @Transactional
    @Override
    public Cheque placeCheque(Map<Pizza, Integer> orderedPizzas) {
        Cheque cheque = new Cheque();
        cheque.setTitle(Cheque.DEFAULT_TITLE);
        cheque.setDate(LocalDateTime.now());
        cheque.setTotalSum(getOrderSum(orderedPizzas));
        return cheque;
    }

    private Double getOrderSum(Map<Pizza, Integer> pizzaMap) {
        return pizzaMap.entrySet().stream()
                .mapToDouble(this::calculateOrderItemSum)
                .reduce(0., Double::sum);
    }

    private double calculateOrderItemSum(Map.Entry<Pizza, Integer> entry) {
        return entry.getKey().getPrice() * entry.getValue();
    }

    public void setDiscountCalculator(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }
}
