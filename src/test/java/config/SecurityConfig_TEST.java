package config;

import com.ys.app.security.CustomAuthenticationFailureHandler;
import com.ys.app.security.CustomAuthenticationSuccessHandler;
import com.ys.app.security.service.CustomUserDetailsService;
import com.ys.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by byun.ys on 4/20/2017.
 */

@EnableWebSecurity
public class SecurityConfig_TEST  {

    @Bean
    private static PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }



    @Configuration
    @Order(1)
    public static class UserWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        UserService userService;

        @Bean
        CustomUserDetailsService customUserDetailsService() {
            return new CustomUserDetailsService(userService);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customUserDetailsService()).passwordEncoder(bCryptPasswordEncoder());

        }

        @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManager();
        }

        @Bean
        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
            return new CustomAuthenticationSuccessHandler();
        }

        @Bean
        CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
            return new CustomAuthenticationFailureHandler();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf();
            http.headers().frameOptions().sameOrigin();

            http.authorizeRequests().antMatchers("/resources/**", "/nimda/login","/nimda/logout").permitAll().antMatchers("/super/**")
                    .hasAnyRole("SU").antMatchers("/nimda").hasAnyRole("SA", "EX", "GS", "EA", "SU")
                    .antMatchers("/nimda/").hasAnyRole("SA", "EX", "GS", "EA", "SU").antMatchers("/nimda/**")
                    .hasAnyRole("SU", "SA").antMatchers("/ses/order/**", "/ses/collection/**")
                    .hasAnyRole("SA", "EX", "GS", "EA", "SU").antMatchers("/ses/concierge/**")
                    .hasAnyRole("SA", "EA", "SU").antMatchers("/ses/queue/**").hasAnyRole("QM", "SU")
                    .antMatchers("/ses/**").hasAnyRole("SA", "EX", "GS", "EA", "SU", "QM").anyRequest().permitAll();

            http.formLogin().loginPage("/nimda/login").usernameParameter("email").passwordParameter("password")
                    .loginProcessingUrl("/nimda/loginProcess").failureUrl("/nimda/login?error=badcredentials")
                    .successHandler(customAuthenticationSuccessHandler())
                    .failureHandler(customAuthenticationFailureHandler()).and().logout().logoutUrl("/nimda/logout")
                    .deleteCookies("JSESSIOINID").logoutSuccessUrl("/nimda/login?logout").and().sessionManagement()
                    .maximumSessions(1).expiredUrl("/nimda/login?expired");
        }
    }
}
