package com.app;

import com.businessdomain.DomainHelper;
import com.businessdomain.Orders;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice(assignableTypes = {OrderController.class})
public class OrderControllerAdvice {
    @Autowired
    OrderService orderService;

    @ModelAttribute(DomainHelper.ORDERS)
    public Orders loadOrder(@RequestParam(name = "orderId", required = false) Orders order) {
        System.out.println("ControllerAdvice: " + order);

        return order;
    }

}
