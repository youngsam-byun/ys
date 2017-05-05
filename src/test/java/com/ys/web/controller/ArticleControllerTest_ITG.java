package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.Article;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.security.factory.WithCustomMockUser;
import com.ys.app.service.ArticleService;
import config.AppConfig_STG;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by byun.ys on 4/18/2017.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig_STG.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("stg")
public class ArticleControllerTest_ITG {

    @Autowired
    ArticleController articleController;

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;


    @Before
    public  void setUp(){
        mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public  void A_home_Thorw404Exception() throws Exception {
        mockMvc.perform(get("/article/listA")).andExpect(status().isNotFound());
    }

    @Test
    public  void B_home_throwCustomException() {
        try {
            mockMvc.perform(get("/article/list/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }

    }

    @Test
    public  void C_home_Return200() throws Exception {
            mockMvc.perform(get("/article/list/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
            .andExpect(view().name("/article/article_list.jsp"));

    }

    @Test
    public  void D_home_ForwardToListingPage() throws Exception{

        mockMvc.perform(get("/article/list")).andExpect(status().isOk())
                .andExpect(view().name("/article/article_list.jsp"));

    }

    @Test
    public  void E_read_ThrowCustomException() throws Exception {

        try {
            mockMvc.perform(get("/article/read/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }

    }

    @Test
    public  void F_read_return200() throws Exception {

        mockMvc.perform(get("/article/read/1")).andExpect(status().isOk())
                .andExpect(view().name("/article/article_read.jsp"));

    }


    @Test
    public  void G_delete_throwCustomException() throws Exception {
        try {
            mockMvc.perform(post("/article/delete/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
    }

    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public  void H_getWrite_returnWriteJspPage() throws  Exception{

        mockMvc.perform(get("/article/create")).andExpect(view().name("/article/article_create.jsp"));


    }

    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public void I_create_returnValidationFailsFortitle() throws Exception{

        mockMvc.perform(post("/article/create").param("categoryId","1")
        .param("no","100").param("level","0").param("sequence","0").param("title","")
                .param("body","body").param("userId","1").param("createTime","20/04/2017")
                        .param("updateTime","20/04/2017").param("noOfRead","0")
                .param("deleted","false")
        ).andExpect(view().name("/article/article_create.jsp"));


    }


    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public void J_create_returnValidationFailsForbody() throws Exception{

        mockMvc.perform(post("/article/create").param("categoryId","1")
                .param("no","100").param("level","0").param("sequence","0").param("title","ddddd")
                .param("body","").param("userId","1").param("createTime","20/04/2017")
                .param("updateTime","20/04/2017").param("noOfRead","0")
                .param("deleted","false")
        ).andExpect(view().name("/article/article_create.jsp"));


    }


    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public void K_create_return200AndListPage() throws Exception{

        mockMvc.perform(post("/article/create").param("categoryId","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("no","100").param("title","ttt")
                .param("body","body").param("userId","354").param("createTime","2017/04/20")
                .param("updateTime","2017/04/20").param("noOfRead","0")
                .param("deleted","false")
        ).andDo(print()).andExpect(redirectedUrl("/article/list/1"));


    }

    @Test
    public  void L_getListBySearch_return200RedirectToHome() throws Exception {



        mockMvc.perform(post("/article/search")
                .param("key","title").param("value","test")
        ).andExpect(status().isOk()).andExpect(model().attributeExists("articleDTOList"));


    }

    @Test
    @WithCustomMockUser(id = 354,roleId = 9,username ="youngsam" )
    public  void M_updateArticle_return200() throws  Exception{

        mockMvc.perform(post("/article/update").param("categoryId","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("no","100").param("title","ttt").param("id","1")
                .param("body","body").param("userId","1").param("createTime","2017/04/20")
                .param("updateTime","2017/04/20").param("noOfRead","0")
                .param("deleted","false")
        ).andDo(print()).andExpect(redirectedUrl("/article/list/1"));


    }
}
