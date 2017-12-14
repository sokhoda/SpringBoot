package com.config;

import com.resource.CustomerResource;
import com.resource.PizzaResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(PizzaResource.class);
        register(CustomerResource.class);
    }

    @PostConstruct
    public void init() {
        configureSwagger();
    }

    private void configureSwagger() {
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setTitle("Spring Boot + Jersey + Swagger");
        config.setVersion("v1");
        config.setContact("Orlando L Otero");
        config.setSchemes(new String[] { "http", "https" });
        config.setHost("localhost:8087");
        config.setBasePath("/api");
        config.setResourcePackage("com.resource");
        config.setPrettyPrint(true);
        config.setScan(true);

    }
}
