package com.ys.app.repo.impl;


import com.ys.app.model.Comment;
import com.ys.app.repo.CommentRepository;
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
public class JdbcCommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    static Comment c;
    static int returnId;

    @Before
    public void init(){
        c = new Comment();
        c.setCategoryId(0);

        c.setBody("hahaha");
        c.setCreateTime(new Date());
        c.setUpdateTime(new Date());
        c.setUserId(0);
        c.setDeleted(false);
    }


    @Test
    public void A_insertDataReturnId(){
        for(int i=0;i<10;i++) {
            returnId = commentRepository.create(c);
        }
        assertThat(returnId).isGreaterThanOrEqualTo(1);
    }


    @Test
    public void B_readByIdShouldEqualToReturnId(){
        Comment c= commentRepository.read(returnId);
        assertThat(c.getId()).isEqualTo(returnId);
    }


    @Test
    @Transactional
    public void C_updateBodyShouldReturnOneRow(){
        c.setId(returnId);
        int returnNumber= commentRepository.update(c);
        assertThat(returnNumber).isEqualTo(1);
    }

    @Test
    @Transactional
    public void D_deleteByIdShouldEqualToOne(){
        int r= commentRepository.delete(returnId);
        assertThat(r).isEqualTo(1);
    }

    @Test
    public  void E_getListShouldContainsOriginalObject(){
        List<Comment> cl= commentRepository.getList(1,10);
        assertThat(cl.size()).isEqualTo(10);
        assertThat(cl.get(0).getBody()).contains(c.getBody());
    }

    @Test
    public  void F_getTotalShouldReturnSizeOfOne(){
        int total= commentRepository.getTotal();
        assertThat(total).isGreaterThanOrEqualTo(10);
    }



}
