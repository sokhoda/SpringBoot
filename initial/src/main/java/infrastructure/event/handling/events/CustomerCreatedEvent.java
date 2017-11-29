package infrastructure.event.handling.events;

import businessdomain.Customer;
import org.springframework.context.ApplicationEvent;

public class CustomerCreatedEvent extends ApplicationEvent {
    private Customer customer;
    private String message;

    public CustomerCreatedEvent(Object source, Customer customer, String message) {
        super(source);
        this.customer = customer;
        this.message = message;
    }

    public String getMessage() {
        return String.format(message, customer);
    }
}
