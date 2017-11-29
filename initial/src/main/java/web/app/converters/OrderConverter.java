package web.app.converters;

import businessdomain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import pizzaservice.OrderService;

import java.util.Objects;

public class OrderConverter implements Converter<String, Orders> {
    @Autowired
    OrderService orderService;

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
