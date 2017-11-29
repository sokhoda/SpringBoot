package infrastructure.event.handling.publishers;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class OrderCreatedEventPublisher {

    @Inject
    private ApplicationEventPublisher applicationEventPublisher;

    public void doPublishEvent(ApplicationEvent orderCreatedEvent) {
        applicationEventPublisher.publishEvent(orderCreatedEvent);
    }
}
