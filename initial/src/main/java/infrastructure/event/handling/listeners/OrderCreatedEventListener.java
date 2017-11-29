package infrastructure.event.handling.listeners;

import infrastructure.event.handling.AsyncListener;
import infrastructure.event.handling.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AsyncListener
public class OrderCreatedEventListener {
    private static final String RECEIVED_NEW_EVENT = "Received new event: %s";

    @EventListener(condition = "#orderCreatedEvent.order.customer.name.length() < 10")
    public void onApplicationEvent(OrderCreatedEvent orderCreatedEvent) {
        String message = String.format(RECEIVED_NEW_EVENT, orderCreatedEvent.getMessage());
        log.info(message);
    }
}
