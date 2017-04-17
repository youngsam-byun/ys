package com.ys.app.repo.impl;


import com.ys.app.model.Category;
import com.ys.app.repo.CategoryRepository;
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
public class JdbcCategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    static Category c;
    static int returnId;

    @Before
    public void init(){
        c = new Category();
        c.setId(1);
        c.setName("categoryName");
        c.setDeleted(false);
    }


    @Test
    public void A_insertDataReturnId(){
        for(int i=0;i<10;i++) {
            returnId = categoryRepository.create(c);
        }
        assertThat(returnId).isGreaterThanOrEqualTo(1);
    }


    @Test
    public void B_readByIdShouldEqualToReturnId(){
        Category c= categoryRepository.read(returnId);
        assertThat(c.getId()).isEqualTo(returnId);
    }

    @Test
    @Transactional
    public void C_updateTitleShouldReturnOneRow(){
        c.setId(returnId);
        c.setName("hihi");
        int returnNumber= categoryRepository.update(c);
        assertThat(returnNumber).isEqualTo(1);
    }

    @Test
    @Transactional
    public void D_deleteByIdShouldEqualToOne(){
        int r= categoryRepository.delete(returnId);
        assertThat(r).isEqualTo(1);
    }

    @Test
    public  void E_getListShouldContainsOriginalObject(){
        List<Category> al= categoryRepository.getList(1,10);
        assertThat(al.size()).isEqualTo(10);
        assertThat(al.get(0).getName()).contains(c.getName());
    }

    @Test
    public  void F_getTotalShouldReturnSizeOfOne(){
        int total= categoryRepository.getTotal();
        assertThat(total).isGreaterThanOrEqualTo(10);
    }

    @Test
    public  void G_getListBySearch(){
        List<Category> al= categoryRepository.getListBySearch(1,10,"name like '%category%' ");
        assertThat(al.size()).isGreaterThanOrEqualTo(1);
        assertThat(al.get(0).getName()).contains(c.getName());
    }

    @Test
    public  void H_getTotalBySearch(){
        int total= categoryRepository.getTotalBySearch("name like '%category%' ");
        assertThat(total).isGreaterThanOrEqualTo(10);
    }

    @Test
    public void I_getAll(){
        List<Category> categoryList=categoryRepository.getAll();
        assertThat(categoryList.size()).isGreaterThanOrEqualTo(10);
    }



}
