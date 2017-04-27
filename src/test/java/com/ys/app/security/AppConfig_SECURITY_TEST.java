package com.ys.app.security;

import config.DataSourceConfig_DEV;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@Import(value = {DataSourceConfig_DEV.class})
@ComponentScan(basePackages = { "com.ys.app" })
@PropertySource(value = { "classpath:property/application.properties","classpath:labels/label.properties","classpath:messages/message.properties","classpath:validations/validation.properties" })
public class AppConfig_SECURITY_TEST extends WebMvcConfigurationSupport {

	private static final String DEFAULT_ENCODING = "UTF-8";

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:labels","classpath:messages","classpath:validations");
		messageSource.setDefaultEncoding(DEFAULT_ENCODING);
		return messageSource;
	}


}
