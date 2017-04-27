package com.ys.app.security;

import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.security.exception.LoginTrialExceedLimitException;
import com.ys.app.security.exception.UserIsEnabledException;
import com.ys.app.security.exception.UserIsNotLockedException;
import com.ys.app.security.service.CustomUserDetailsService;
import com.ys.app.service.UserService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by byun.ys on 4/21/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig_SECURITY_TEST.class})
@ActiveProfiles("dev")
public class CustomUserDetailsServiceTest {

    @Autowired
    UserService userService;


    CustomUserDetailsService customUserDetailsService;



    @Before
    public  void setUp(){
        customUserDetailsService=new CustomUserDetailsService(userService);
    }

    @Test(expected = UsernameNotFoundException.class)
    public  void A__loadByUserName_throwUserNameNotFoundException(){
        customUserDetailsService.loadUserByUsername("hahaha@email.com");
    }

    @Test(expected = UserIsNotLockedException.class)
    public   void B_loadByUserName_throwUserIsNotLockedException(){

        customUserDetailsService.loadUserByUsername("locked@email.com");
    }

    @Test(expected = UserIsEnabledException.class)
    public   void C_loadByUserName_throwUserIsEnabledException(){

        customUserDetailsService.loadUserByUsername("enabled@email.com");
    }


    @Test(expected = LoginTrialExceedLimitException.class)
    public   void D_loadByUserName_throwLoginTrialExceedLimitException(){

        customUserDetailsService.loadUserByUsername("exceed@email.com");
    }


    @Test
    public  void test(){

         CustomUserDetails customUserDetails=(CustomUserDetails)customUserDetailsService.loadUserByUsername("hah@email.com");
         User user=customUserDetails.getUser();
         assertThat(user.getUsername()).isEqualTo("ys username");
         assertThat(user.getRoleId()).isEqualTo(Role.USER.getId());

    }

}
