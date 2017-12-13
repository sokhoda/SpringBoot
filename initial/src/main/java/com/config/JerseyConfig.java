package com.config;

import com.resource.CustomerResource;
import com.resource.PizzaResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(PizzaResource.class);
        register(CustomerResource.class);
    }
}
