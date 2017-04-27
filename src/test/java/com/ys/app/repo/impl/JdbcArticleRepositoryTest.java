package com.ys.app.repo.impl;


import com.ys.app.model.Article;
import com.ys.app.repo.ArticleRepository;
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
public class JdbcArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    static Article a;
    static int returnId;

    @Before
    public void init(){
        a = new Article();
        a.setCategoryId(0);

        a.setTitle("test");
        a.setBody("hahaha");
        a.setCreateTime(new Date());
        a.setUpdateTime(new Date());
        a.setUserId(0);
        a.setDeleted(false);
    }


    @Test
    public void A_insertDataReturnId(){
        for(int i=0;i<10;i++) {
            returnId = articleRepository.create(a);
        }
        assertThat(returnId).isGreaterThanOrEqualTo(1);
    }


    @Test
    public void B_readByIdShouldEqualToReturnId(){
        Article a=articleRepository.read(returnId);
        assertThat(a.getId()).isEqualTo(returnId);
    }


    @Test
    @Transactional
    public void C_updateTitleShouldReturnOneRow(){
        a.setId(returnId);
        a.setTitle("hihi");
        int returnNumber=articleRepository.update(a);
        assertThat(returnNumber).isEqualTo(1);
    }

    @Test
    @Transactional
    public void D_deleteByIdShouldEqualToOne(){
        int r=articleRepository.delete(returnId);
        assertThat(r).isEqualTo(1);
    }

    @Test
    public  void E_getListShouldContainsOriginalObject(){
        List<Article> al=articleRepository.getList(1,10);
        assertThat(al.size()).isEqualTo(10);
        assertThat(al.get(0).getTitle()).contains(a.getTitle());
    }

    @Test
    public  void F_getTotalShouldReturnSizeOfOne(){
        int total=articleRepository.getTotal();
        assertThat(total).isGreaterThanOrEqualTo(10);
    }

    @Test
    public  void G_getListBySearch(){
        List<Article> al=articleRepository.getListBySearch(1,10,"title like '%te%' ");
        assertThat(al.size()).isGreaterThanOrEqualTo(1);
        assertThat(al.get(0).getTitle()).contains(a.getTitle());
    }

    @Test
    public  void H_getTotalBySearch(){
        int total=articleRepository.getTotalBySearch("title like '%te%' ");
        assertThat(total).isGreaterThanOrEqualTo(10);
    }


}
