package infrastructure.event.handling.publishers;

import infrastructure.event.handling.events.CustomerCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class CustomerCreatedEventPublisher {

    @Inject
    private ApplicationEventPublisher applicationEventPublisher;

    public void doPublishEvent(CustomerCreatedEvent customerCreatedEvent) {
        applicationEventPublisher.publishEvent(customerCreatedEvent);
    }
}
