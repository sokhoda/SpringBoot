package web.app.controllers;

import businessdomain.DomainHelper;
import businessdomain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import pizzaservice.OrderService;

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
