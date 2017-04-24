package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.Article;
import com.ys.app.model.dto.ArticleDTO;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    }

    @Test
    public  void B_home_throwCustomException() {
        try {
            mockMVC.perform(get("/article/list/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
    }

    @Test
    public  void C_home_Return200() throws Exception {
            mockMVC.perform(get("/article/list/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
            .andExpect(view().name("/article/list.jsp"));
            verify(articleService,times(1)).getList(1,10);
            verify(articleService,times(1)).getPagination(1,10);
    }

    @Test
    public  void D_home_ForwardToListingPage() throws Exception{

        mockMVC.perform(get("/article/list")).andExpect(status().isOk())
                .andExpect(view().name("/article/list.jsp"));
        verify(articleService,times(1)).getList(1,10);
        verify(articleService,times(1)).getPagination(1,10);

    }

    @Test
    public  void E_read_ThrowCustomException() throws Exception {

        try {
            mockMVC.perform(get("/article/read/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }


    }

    @Test
    public  void F_read_return200() throws Exception {
        when(articleService.readArticle(431)).thenReturn(new ArticleDTO());
        mockMVC.perform(get("/article/read/431")).andExpect(status().isOk())
                .andExpect(view().name("/article/read.jsp"));;
        verify(articleService,times(1)).readArticle(431);
    }


    @Test
    public  void G_delete_throwCustomException() throws Exception {
        try {
            mockMVC.perform(post("/article/delete/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
    }

    @Test
    public  void H_get_write_returnWriteJspPage() throws  Exception{

        mockMVC.perform(get("/article/write")).andExpect(view().name("/article/write.jsp"));
    }

    @Test
    public void I_write_returnValidationFailsFortitle() throws Exception{

        mockMVC.perform(post("/article/write").param("categoryId","1")
        .param("no","100").param("level","0").param("sequence","0").param("title","")
                .param("body","body").param("userId","1").param("createTime","20/04/2017")
                        .param("updateTime","20/04/2017").param("noOfRead","0")
                .param("deleted","false")
        ).andExpect(view().name("/article/write.jsp"));

    }


    @Test
    public void J_write_returnValidationFailsForbody() throws Exception{

        mockMVC.perform(post("/article/write").param("categoryId","1")
                .param("no","100").param("level","0").param("sequence","0").param("title","ddddd")
                .param("body","").param("userId","1").param("createTime","20/04/2017")
                .param("updateTime","20/04/2017").param("noOfRead","0")
                .param("deleted","false")
        ).andExpect(view().name("/article/write.jsp"));



    }


    @Test
    public void K_write_return200AndListPage() throws Exception{

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



        //when(articleService.writeArticle(a, SecurityContextHolder.getContext())).thenReturn(true);
        when(articleService.writeArticle(any(),any())).thenReturn(true);

        mockMVC.perform(post("/article/write").param("categoryId","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("no","100").param("level","0").param("sequence","0").param("title","ttt")
                .param("body","body").param("userId","1").param("createTime","2017/04/20")
                .param("updateTime","2017/04/20").param("noOfRead","0")
                .param("deleted","false")
        ).andDo(print()).andExpect(view().name("/article/list.jsp"));

        verify(articleService,times(1)).writeArticle(any(),any());
    }
}
