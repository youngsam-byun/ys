package com.ys.app.oauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.annotation.SecurityTestExecutionListeners;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by byun.ys on 5/12/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@TestExecutionListeners(listeners = {WithSecurityContextTestExecutionListener.class})
public class OAuthConfigTest {

    @Configuration
    @ComponentScan(basePackages = "com.ys.app.oauth")
    public static class config{}

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Test
    public  void securityConfigurationLoads(){

    }
}
