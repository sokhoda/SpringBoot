package com.app.converters;

import com.businessdomain.Orders;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class OrderConverter implements Converter<String, Orders> {
    @Autowired
    private OrderService orderService;

    @Override
    public Orders convert(String orderIdStr) {
        System.out.println("Convert " + orderIdStr);
        Orders result = new Orders();
        if (!StringUtils.isEmpty(orderIdStr)) {
            Long orderId = Long.valueOf(orderIdStr);
            result = Objects.nonNull(orderId) ? orderService.find(orderId) : null;
        }
        return result;
    }
}
