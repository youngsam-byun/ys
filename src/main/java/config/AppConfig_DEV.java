package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@Import(DataSourceConfig_DEV.class)
@PropertySource(value = { "classpath:db/db_dev.properties" })
@ComponentScan(basePackages = { "com.ys.app" })
@ActiveProfiles(profiles = {"dev"})
public class AppConfig_DEV extends WebMvcConfigurationSupport {

	public static final String defaultEncoding = "UTF-8";

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:property/strings", "classpath:property/validation");
		messageSource.setDefaultEncoding(AppConfig_DEV.defaultEncoding);
		return messageSource;
	}



}
