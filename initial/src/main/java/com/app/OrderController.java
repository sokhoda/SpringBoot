package com.app;

import com.app.converters.PizzaConverter;
import com.businessdomain.Customer;
import com.businessdomain.DomainHelper;
import com.businessdomain.Orders;
import com.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class OrderController {
    public static final String USER = "user";

    @Inject
    private OrderService orderService;
    @Inject
    private PizzaConverter pizzaConverter;


    @RequestMapping(Routes.ORDERS_LIST)
    public String getAllOrders(ModelMap model) {
        List<Orders> orderList = orderService.findAll();
        model.addAttribute(DomainHelper.ORDERSLIST, orderList);
//        model.addAttribute(DomainHelper.ORDERSLIST_FOR_XML_MAPPING, new OrdersList(orderList));
        return Routes.ORDER_LIST_PAGE;
    }

    @RequestMapping(Routes.ORDER_CREATE)
    public String orderCreate(@RequestParam(required = false) Long orderId, Model model, HttpSession session) {
        orderService.doCreateOrder(model, session);
        return Routes.ORDER_EDIT_PAGE;
    }

    @RequestMapping(value = Routes.ORDER_ADDNEW, method = RequestMethod.POST)
    public String addNewOrder(@RequestParam("orderedPizzaIds") String orderedPizzaIds, HttpSession session) {
        Customer customer = (Customer) session.getAttribute(USER);
        orderService.buildOrder(orderedPizzaIds, customer, pizzaConverter);
        return Routes.REDIRECT_DASHBOARD_PAGE;
    }
}
