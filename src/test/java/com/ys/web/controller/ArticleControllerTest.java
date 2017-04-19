package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.service.ArticleService;
import config.AppConfig_TEST;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by byun.ys on 4/18/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig_TEST.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleControllerTest {

    @Mock
    ArticleService articleService;

    @InjectMocks
    ArticleController articleController;

    private MockMvc mockMVC;

    @Before
    public  void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMVC= MockMvcBuilders.standaloneSetup(articleController).build();
    }


    @Test
    public  void A_homePageTestShouldThorw404Exception() throws Exception {
        mockMVC.perform(get("/article/listA")).andExpect(status().isNotFound());
    }

    @Test
    public  void B_homePageTestShouldReturn200() throws Exception {
            mockMVC.perform(get("/article/list/1")).andExpect(status().isOk())
            .andExpect(view().name("/article/list.jsp"));
            verify(articleService,times(1)).getList(1,10);
            verify(articleService,times(1)).getPagination(1,10);
    }

    @Test
    public  void C_homePageShouldForwardToListingPage() throws Exception{

        mockMVC.perform(get("/article/list")).andExpect(status().isOk())
                .andExpect(view().name("/article/list.jsp"));
        verify(articleService,times(1)).getList(1,10);
        verify(articleService,times(1)).getPagination(1,10);

    }

    @Test
    public  void D_readArticleShouldThrowCustomException() throws Exception {
        when(articleService.readArticle(-1)).thenReturn(null);

        try {
            mockMVC.perform(get("/article/read/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }

    }

    @Test
    public  void E_readArticleShouldReturnOk() throws Exception {
        when(articleService.readArticle(431)).thenReturn(new ArticleDTO());
        mockMVC.perform(get("/article/read/431")).andExpect(status().isOk())
                .andExpect(view().name("/article/read.jsp"));;
        verify(articleService,times(1)).readArticle(431);
    }


    @Test
    public  void F_deleteArticleShouldReturn404() throws Exception {
        mockMVC.perform(post("/article/delete/")).andExpect(status().isNotFound());
    }
//
//    @Test
//    public  void G_deleteArticleShouldThorwNullPointerException() throws Exception {
//        mockMVC.perform(post("/article/delete/12"));
//    }
}
