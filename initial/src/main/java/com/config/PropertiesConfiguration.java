package com.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PropertiesConfiguration {

    @Bean
    public PropertyPlaceholderConfigurer properties() {
        final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
//        ppc.setIgnoreUnresolvablePlaceholders(true);
        ppc.setIgnoreResourceNotFound(true);

        final List<Resource> resourceLst = new ArrayList<Resource>();

        resourceLst.add(new ClassPathResource("misc.properties"));
        resourceLst.add(new ClassPathResource("mail_sender.properties"));
//        resourceLst.add(new FileSystemResource("/etc/myapp/overriding.properties"));

        ppc.setLocations(resourceLst.toArray(new Resource[]{}));

        return ppc;
    }
}