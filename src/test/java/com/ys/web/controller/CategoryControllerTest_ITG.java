package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.Category;
import com.ys.app.security.factory.WithCustomMockUser;
import com.ys.app.service.CategoryService;
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
public class CategoryControllerTest_ITG {

    @Autowired
    CategoryController categoryController;

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;

    @Before
    public  void setUp(){
        mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public  void A_home_Thorw404Exception() throws Exception {
        mockMvc.perform(get("/category/listA")).andExpect(status().isNotFound());

    }

    @Test
    public  void B_home_throwCustomException() {
        try {
            mockMvc.perform(get("/category/list/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
    }

    @Test
    public  void C_home_Return200() throws Exception {
            mockMvc.perform(get("/category/list/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
            .andExpect(view().name("/category/category_list.jsp"));

    }

    @Test
    public  void D_home_ForwardToListingPage() throws Exception{

        mockMvc.perform(get("/category/list")).andExpect(status().isOk())
                .andExpect(view().name("/category/category_list.jsp"));
    }

    @Test
    public  void E_read_ThrowCustomException() throws Exception {

        try {
            mockMvc.perform(get("/category/read/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }

    }

    @Test
    public  void F_read_return200() throws Exception {
        mockMvc.perform(get("/category/read/1")).andExpect(status().isOk())
                .andExpect(view().name("/category/category_read.jsp"));

    }


    @Test
    @WithCustomMockUser(id = 354,roleId = 9,username ="youngsam" )
    public  void G_delete_throwCustomException() throws Exception {
        try {
            mockMvc.perform(post("/category/delete/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
    }

    @Test
    public  void H_getWrite_returnWriteJspPage() throws  Exception{

        mockMvc.perform(get("/category/create")).andExpect(view().name("/category/category_create.jsp"));

    }



    @Test
    @WithCustomMockUser(id = 354,roleId = 9,username ="youngsam" )
    public void J_create_returnValidationFailsForbody() throws Exception{

        mockMvc.perform(post("/category/create").param("categoryId","1")
                .param("no","100").param("level","0").param("sequence","0").param("title","ddddd")
                .param("name","").param("userId","1").param("createTime","20/04/2017")
                .param("updateTime","20/04/2017").param("noOfRead","0")
                .param("deleted","false")
        ).andExpect(view().name("/category/category_create.jsp"));

    }


    @Test
    @WithCustomMockUser(id = 354,roleId = 9,username ="youngsam" )
    public void K_create_return200AndListPage() throws Exception{

        Category c=new Category();
        c.setId(0);
        c.setName("r");
        c.setCreateTime(new GregorianCalendar(2017,04,20).getTime());
        c.setUpdateTime(new GregorianCalendar(2017,04,20).getTime());
        c.setDeleted(false);




        mockMvc.perform(post("/category/create").param("categoryId","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("no","100").param("level","0").param("sequence","0").param("title","ttt")
                .param("name","name").param("userId","1").param("createTime","2017/04/20")
                .param("updateTime","2017/04/20").param("noOfRead","0")
                .param("deleted","false")
        ).andDo(print()).andExpect(redirectedUrl("/category/list/1"));

    }

    @Test
    public  void L_getListBySearch_return200RedirectToHome() throws Exception {



        mockMvc.perform(post("/category/search")
                .param("key","name").param("value","cate")
        ).andExpect(status().isOk()).andExpect(model().attributeExists("categoryList"));


    }

    @Test
    @WithCustomMockUser(id = 354,roleId = 9,username ="youngsam" )
    public  void M_updateCategory_return200() throws  Exception{

        Category c=new Category();
        c.setId(0);
        c.setName("name");
        c.setCreateTime(new GregorianCalendar(2017,04,20).getTime());
        c.setUpdateTime(new GregorianCalendar(2017,04,20).getTime());
        c.setDeleted(false);


        Principal principal=(Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //when(categoryService.create(a, SecurityContextHolder.getContext())).thenReturn(true);


        mockMvc.perform(post("/category/update").param("id","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("name","name").param("userId","1").param("createTime","2017/04/20")
                .param("updateTime","2017/04/20")
                .param("deleted","false")
        ).andDo(print()).andExpect(redirectedUrl("/category/list/1"));


    }
}
