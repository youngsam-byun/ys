package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.Article;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.security.factory.WithCustomMockUser;
import com.ys.app.service.ArticleService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig_CONTROLLER_TEST.class})
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
    public  void A_home_Thorw404Exception() throws Exception {
        mockMVC.perform(get("/article/listA")).andExpect(status().isNotFound());
        verifyNoMoreInteractions(articleService);
    }

    @Test
    public  void B_home_throwCustomException() {
        try {
            mockMVC.perform(get("/article/list/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
        verifyNoMoreInteractions(articleService);
    }

    @Test
    public  void C_home_Return200() throws Exception {
            mockMVC.perform(get("/article/list/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
            .andExpect(view().name("/article/article_list"));
            verify(articleService,times(1)).getList(1,10);
            verify(articleService,times(1)).getPagination(1,10);

        verifyNoMoreInteractions(articleService);
    }

    @Test
    public  void D_home_ForwardToListingPage() throws Exception{

        mockMVC.perform(get("/article/list")).andExpect(status().isOk())
                .andExpect(view().name("/article/article_list"));
        verify(articleService,times(1)).getList(1,10);
        verify(articleService,times(1)).getPagination(1,10);

        verifyNoMoreInteractions(articleService);
    }

    @Test
    public  void E_read_ThrowCustomException() throws Exception {

        try {
            mockMVC.perform(get("/article/read/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }

        verifyNoMoreInteractions(articleService);
    }

    @Test
    public  void F_read_return200() throws Exception {
        when(articleService.read(431)).thenReturn(new ArticleDTO());
        mockMVC.perform(get("/article/read/431")).andExpect(status().isOk())
                .andExpect(view().name("/article/article_read"));
        verify(articleService,times(1)).read(431);

        verifyNoMoreInteractions(articleService);
    }


    @Test
    public  void G_delete_throwCustomException() throws Exception {
        try {
            mockMVC.perform(post("/article/delete/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
        verifyNoMoreInteractions(articleService);
    }

    @Test
    public  void H_getWrite_returnWriteJspPage() throws  Exception{

        mockMVC.perform(get("/article/create")).andExpect(view().name("/article/article_create"));

        verifyNoMoreInteractions(articleService);
    }

    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public void I_create_returnValidationFailsFortitle() throws Exception{

        mockMVC.perform(post("/article/create").param("categoryId","1")
        .param("no","100").param("level","0").param("sequence","0").param("title","")
                .param("body","body").param("userId","1").param("createTime","20/04/2017")
                        .param("updateTime","20/04/2017").param("noOfRead","0")
                .param("deleted","false")
        ).andExpect(view().name("/article/article_create"));

        verifyNoMoreInteractions(articleService);
    }


    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public void J_create_returnValidationFailsForbody() throws Exception{

        mockMVC.perform(post("/article/create").param("categoryId","1")
                .param("no","100").param("level","0").param("sequence","0").param("title","ddddd")
                .param("body","").param("userId","1").param("createTime","20/04/2017")
                .param("updateTime","20/04/2017").param("noOfRead","0")
                .param("deleted","false")
        ).andExpect(view().name("/article/article_create"));


        verifyNoMoreInteractions(articleService);
    }


    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public void K_create_return200AndListPage() throws Exception{

        Article a=new Article();
        a.setId(0);
        a.setCategoryId(1);
        a.setTitle("ttt");
        a.setBody("body");
        a.setUserId(1);
        a.setCreateTime(new GregorianCalendar(2017,04,20).getTime());
        a.setUpdateTime(new GregorianCalendar(2017,04,20).getTime());
        a.setNoOfRead(0);
        a.setDeleted(false);



        //when(articleService.create(a, SecurityContextHolder.getContext())).thenReturn(true);
        when(articleService.create(any(),any())).thenReturn(true);

        mockMVC.perform(post("/article/create").param("categoryId","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("no","100").param("level","0").param("sequence","0").param("title","ttt")
                .param("body","body").param("userId","1").param("createTime","2017/04/20")
                .param("updateTime","2017/04/20").param("noOfRead","0")
                .param("deleted","false")
        ).andDo(print()).andExpect(redirectedUrl("/article/list/1"));

        verify(articleService,times(1)).create(any(),any());
        verifyNoMoreInteractions(articleService);
    }

    @Test
    public  void L_getListBySearch_return200RedirectToHome() throws Exception {

        List<ArticleDTO> articleDTOList=new ArrayList<>();
        when(articleService.getListBySearch(1,10,"title like '%youngs%'")).thenReturn(articleDTOList);


        mockMVC.perform(post("/article/search")
                .param("key","title").param("value","youngs")
        ).andExpect(status().isOk()).andExpect(model().attribute("articleDTOList",articleDTOList));

        verify(articleService,times(1)).getListBySearch(1,10,"title like '%youngs%'");
        verify(articleService,times(1)).getPaginationBySearch(1,10,"title like '%youngs%'");
        verifyNoMoreInteractions(articleService);
    }

    @Test
    @WithCustomMockUser
    public  void M_updateArticle_return200() throws  Exception{

        Article a=new Article();
        a.setId(0);
        a.setCategoryId(1);
        a.setTitle("ttt");
        a.setBody("body");
        a.setUserId(1);
        a.setCreateTime(new GregorianCalendar(2017,04,20).getTime());
        a.setUpdateTime(new GregorianCalendar(2017,04,20).getTime());
        a.setNoOfRead(0);
        a.setDeleted(false);


        Principal principal=(Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //when(articleService.create(a, SecurityContextHolder.getContext())).thenReturn(true);
        when(articleService.update(any(Article.class),any())).thenReturn(true);

        mockMVC.perform(post("/article/update").param("categoryId","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("no","100").param("level","0").param("sequence","0").param("title","ttt")
                .param("body","body").param("userId","1").param("createTime","2017/04/20")
                .param("updateTime","2017/04/20").param("noOfRead","0")
                .param("deleted","false")
        ).andDo(print()).andExpect(redirectedUrl("/article/list/1"));

        verify(articleService,times(1)).update(any(Article.class),any());
        verifyNoMoreInteractions(articleService);

    }
}
