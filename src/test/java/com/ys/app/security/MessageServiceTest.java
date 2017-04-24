package com.ys.app.security;

import com.ys.web.controller.AppConfig_CONTROLLER_TEST;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by byun.ys on 4/20/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig_CONTROLLER_TEST.class})
public class MessageServiceTest {

    @PreAuthorize("authenticated")
    public String testMethod(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return "hello" +authentication;
    }

    @Test
    public void notAuthenticated_throwAuthenticationCredentialsNotFoundException(){
        try {
            testMethod();
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(AuthenticationCredentialsNotFoundException.class);
        }
    }

    @Test
    @WithMockUser(value = "youngsam",roles = {"ADMIN"})
    public  void withMockUser_OK(){
        testMethod();
    }

    @Test
    @WithAnonymousUser
    public void withAnonyMousUser_throwAuthenticationCredentialsNotFoundException(){
        try {
            testMethod();
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(AuthenticationCredentialsNotFoundException.class);
        }
    }




}
