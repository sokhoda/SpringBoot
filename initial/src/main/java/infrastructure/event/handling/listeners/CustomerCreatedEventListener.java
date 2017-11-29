package infrastructure.event.handling.listeners;

import infrastructure.event.handling.events.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerCreatedEventListener implements ApplicationListener<CustomerCreatedEvent> {

    private static final String RECEIVED_NEW_EVENT = "Received new event: %s";

    @Override
    public void onApplicationEvent(CustomerCreatedEvent event) {
        String message = String.format(RECEIVED_NEW_EVENT, event.getMessage());
        log.info(message);
    }
}
