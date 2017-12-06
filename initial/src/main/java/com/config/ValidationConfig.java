package com.config;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    private static final String JAVAX_VALIDATION_MESSAGE_PROPERTIES = "javax_validation/javax_validation_message";
    private static final String SPRING_VALIDATION_MESSAGE_PROPERTIES = "spring_validation/message";

    @Bean
    public LocalValidatorFactoryBean validatorFactory(MessageSource messageSource) {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setMessageInterpolator(messageInterpolator());
        return factory;
    }

    @Bean
    public ResourceBundleMessageInterpolator messageInterpolator() {
        PlatformResourceBundleLocator userResourceBundleLocator = new PlatformResourceBundleLocator(JAVAX_VALIDATION_MESSAGE_PROPERTIES);
        return new ResourceBundleMessageInterpolator(userResourceBundleLocator);
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(SPRING_VALIDATION_MESSAGE_PROPERTIES);
        return messageSource;
    }
}