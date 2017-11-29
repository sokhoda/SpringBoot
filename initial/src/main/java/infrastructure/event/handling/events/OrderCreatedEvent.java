package infrastructure.event.handling.events;

import businessdomain.Orders;
import org.springframework.context.ApplicationEvent;

public class OrderCreatedEvent extends ApplicationEvent {
    private Orders order;
    private String message;

    public OrderCreatedEvent(Object source, Orders order, String message) {
        super(source);
        this.order = order;
        this.message = message;
    }

    public Orders getOrder() {
        return order;
    }

    public String getMessage() {
        return String.format(message, order);
    }
}
