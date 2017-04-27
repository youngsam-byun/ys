package com.ys.app.repo.impl;


import com.ys.app.model.User;
import com.ys.app.repo.UserRepository;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by byun.ys on 4/11/2017.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig_REPO_TEST.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@ActiveProfiles("dev")
public class JdbcUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    static User user;
    static int returnId;

    private  static  String lastEmail;
    @Before
    public void init(){
        user =new User();
        user.setProviderId("ys");
        user.setConnectionId("1234ABCD");
        user.setProviderConnectionid("ys1234ABCD");
        user.setRank(1);
        user.setDisplayName("ys test");
        user.setProfileUrl("http://www.youngsam.com/profile");
        user.setImageUrl("http://www.google.com/image");
        user.getRefreshToken();
        user.setExpireTime(10000L);
        user.setEmail("hah@email.com");
        user.setUsername("ys username");
        user.setPassword("ys password");
        user.setRoleId(1);
        user.setEnabled(true);
        user.setNotLocked(true);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

    }


    @Test
    public void A_create_returnDataReturnId(){

        for(int i=0;i<10;i++) {
            lastEmail=i+"@email.com";
            user.setEmail(lastEmail);
            returnId = userRepository.create(user);
        }
        assertThat(returnId).isGreaterThanOrEqualTo(1);
    }


    @Test
    public void B_readById_EqualToReturnId(){
       User u=userRepository.read(returnId);
        assertThat(u.getId()).isEqualTo(returnId);
    }

    @Test
    public void B_readByEmail_EqualToReturnId(){
        User u=userRepository.readByEmail("1@email.com");
        assertThat(u.getEmail()).isEqualTo("1@email.com");

    }

    @Test
    @Transactional
    public void C_update_Return1(){
        User u=new User();
        u.setId(returnId);
        u.setUsername("hahaha");
        int returnNumber=userRepository.update(u);
        assertThat(returnNumber).isEqualTo(1);
    }

    @Test
    @Transactional
    public void C_updatePassword_Return1(){
        user.setPassword("new password");
        user.setEmail(lastEmail);
        int returnNumber=userRepository.updatePassword(user);
        assertThat(returnNumber).isEqualTo(1);
    }

    @Test
    @Transactional
    public void C_updateTrialCountByOne_Return1(){
        User u=new User();
        u.setId(1);
        u.setEmail("1@email.com");
        int returnNumber=userRepository.updateTrialCountByOne(u.getEmail(),new Date());
        assertThat(returnNumber).isGreaterThanOrEqualTo(1);
    }

    @Test
    @Transactional
    public void D_deleteById_equalToOne(){
        int r=userRepository.delete(returnId);
        assertThat(r).isEqualTo(1);
    }
//
    @Test
    public  void E_getList_containsOriginalObject(){
        List<User> ul=userRepository.getList(1,10);
        assertThat(ul.size()).isEqualTo(10);
        assertThat(ul.get(0).getUsername()).contains(user.getUsername());
    }

    @Test
    public  void F_getTotal_returnSizeOfOne(){
        int total=userRepository.getTotal();
        assertThat(total).isGreaterThanOrEqualTo(10);
    }

    @Test
    public  void G_getListBySearch(){
        List<User> ul=userRepository.getListBySearch(1,10,"username like '%ys%' ");
        assertThat(ul.size()).isGreaterThanOrEqualTo(1);
        assertThat(ul.get(0).getUsername()).contains(user.getUsername());
    }

    @Test
    public  void H_getTotalBySearch(){
        int total=userRepository.getTotalBySearch("username like '%ys%' ");
        assertThat(total).isGreaterThanOrEqualTo(10);
    }


}
