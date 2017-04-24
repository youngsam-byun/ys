package com.ys.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by byun.ys on 4/18/2017.
 */
@Configuration
@PropertySource(value = { "classpath:property/application.properties","classpath:labels/label.properties","classpath:messages/message.properties","validations/validation.properties" })
public class AppConfig_CONTROLLER_TEST {

    private static final String DEFAULT_ENCODING = "UTF-8";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:properties","classpath:labels","classpath:messages","classpath:validations");
        messageSource.setDefaultEncoding(DEFAULT_ENCODING);
        return messageSource;
    }
}
