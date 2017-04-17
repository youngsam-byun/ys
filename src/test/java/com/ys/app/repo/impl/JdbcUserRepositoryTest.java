package com.ys.app.repo.impl;


import com.ys.app.model.User;
import com.ys.app.repo.UserRepository;
import config.AppConfig_DEV;
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


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig_DEV.class})
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    static User user;
    static int returnId;

    @Before
    public void init(){
        user =new User();
        user.setProviderid("ys");
        user.setConnectionid("1234ABCD");
        user.setProviderConnectionid("ys1234ABCD");
        user.setRank(1);
        user.setDisplayname("ys test");
        user.setProfileUrl("http://www.youngsam.com/profile");
        user.setImageurl("http://www.google.com/image");
        user.setAccesstoken("randomalaphanumeric");
        user.setSecret("secret randomString");
        user.getRefreshtoken();
        user.setExpiretime(10000L);
        user.setUsername("ys username");
        user.setPassword("ys password");
        user.setRoleid(1);
        user.setStr("randomStr");
        user.setEnabled(true);
        user.setNotlocked(true);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

    }


    @Test
    public void A_insertDataReturnId(){
        for(int i=0;i<10;i++) {
            returnId = userRepository.create(user);
        }
        assertThat(returnId).isGreaterThanOrEqualTo(1);
    }


    @Test
    public void B_readByIdShouldEqualToReturnId(){
       User u=userRepository.read(returnId);
        assertThat(u.getId()).isEqualTo(returnId);
    }


    @Test
    @Transactional
    public void C_updateUsernameShouldReturnOneRow(){
        User u=new User();
        u.setId(returnId);
        u.setUsername("hahaha");
        int returnNumber=userRepository.update(u);
        assertThat(returnNumber).isEqualTo(1);
    }

    @Test
    @Transactional
    public void D_deleteByIdShouldEqualToOne(){
        int r=userRepository.delete(returnId);
        assertThat(r).isEqualTo(1);
    }
//
    @Test
    public  void E_getListShouldContainsOriginalObject(){
        List<User> ul=userRepository.getList(1,10);
        assertThat(ul.size()).isEqualTo(10);
        assertThat(ul.get(0).getUsername()).contains(user.getUsername());
    }

    @Test
    public  void F_getTotalShouldReturnSizeOfOne(){
        int total=userRepository.getTotal();
        assertThat(total).isGreaterThan(10);
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
