package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.Category;
import com.ys.app.security.factory.WithCustomMockUser;
import com.ys.app.service.CategoryService;
import com.ys.app.util.WithSecurityContextTestExcecutionListener;
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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
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
public class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    private MockMvc mockMVC;

    @Before
    public  void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMVC= MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public  void A_home_Thorw404Exception() throws Exception {
        mockMVC.perform(get("/category/listA")).andExpect(status().isNotFound());
        verifyNoMoreInteractions(categoryService);
    }

    @Test
    public  void B_home_throwCustomException() {
        try {
            mockMVC.perform(get("/category/list/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
        verifyNoMoreInteractions(categoryService);
    }

    @Test
    public  void C_home_Return200() throws Exception {
            mockMVC.perform(get("/category/list/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
            .andExpect(view().name("/category/category_list"));
            verify(categoryService,times(1)).getList(1,10);
            verify(categoryService,times(1)).getPagination(1,10);

        verifyNoMoreInteractions(categoryService);
    }

    @Test
    public  void D_home_ForwardToListingPage() throws Exception{

        mockMVC.perform(get("/category/list")).andExpect(status().isOk())
                .andExpect(view().name("/category/category_list"));
        verify(categoryService,times(1)).getList(1,10);
        verify(categoryService,times(1)).getPagination(1,10);

        verifyNoMoreInteractions(categoryService);
    }

    @Test
    public  void E_read_ThrowCustomException() throws Exception {

        try {
            mockMVC.perform(get("/category/read/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }

        verifyNoMoreInteractions(categoryService);
    }

    @Test
    public  void F_read_return200() throws Exception {
        when(categoryService.read(431)).thenReturn(new Category());
        mockMVC.perform(get("/category/read/431")).andExpect(status().isOk())
                .andExpect(view().name("/category/category_read"));
        verify(categoryService,times(1)).read(431);

        verifyNoMoreInteractions(categoryService);
    }


    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public  void G_delete_throwCustomException() throws Exception {
        try {
            mockMVC.perform(post("/category/delete/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
        verifyNoMoreInteractions(categoryService);
    }

    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public  void H_getWrite_returnWriteJspPage() throws  Exception{

        mockMVC.perform(get("/category/create")).andExpect(view().name("/category/category_create"));

        verifyNoMoreInteractions(categoryService);
    }



    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public void J_create_returnValidationFailsForbody() throws Exception{

        mockMVC.perform(post("/category/create").param("categoryId","1")
                .param("no","100").param("level","0").param("sequence","0").param("title","ddddd")
                .param("name","").param("userId","1").param("createTime","20/04/2017")
                .param("updateTime","20/04/2017").param("noOfRead","0")
                .param("deleted","false")
        ).andExpect(view().name("/category/category_create"));


        verifyNoMoreInteractions(categoryService);
    }


    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public void K_create_return200AndListPage() throws Exception{

        Category c=new Category();
        c.setId(0);
        c.setName("r");
        c.setCreateTime(new GregorianCalendar(2017,04,20).getTime());
        c.setUpdateTime(new GregorianCalendar(2017,04,20).getTime());
        c.setDeleted(false);



        //when(categoryService.create(a, SecurityContextHolder.getContext())).thenReturn(true);
        when(categoryService.create(any(),any())).thenReturn(true);

        mockMVC.perform(post("/category/create").param("categoryId","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("no","100").param("level","0").param("sequence","0").param("title","ttt")
                .param("name","name").param("userId","1").param("createTime","2017/04/20")
                .param("updateTime","2017/04/20").param("noOfRead","0")
                .param("deleted","false")
        ).andDo(print()).andExpect(redirectedUrl("/category/list/1"));

        verify(categoryService,times(1)).create(any(),any());
        verifyNoMoreInteractions(categoryService);
    }

    @Test
    public  void L_getListBySearch_return200RedirectToHome() throws Exception {

        List<Category> categoryList=new ArrayList<>();
        when(categoryService.getListBySearch(1,10,"name like '%youngs%'")).thenReturn(categoryList);


        mockMVC.perform(post("/category/search")
                .param("key","name").param("value","youngs")
        ).andExpect(status().isOk()).andExpect(model().attribute("categoryList",categoryList));

        verify(categoryService,times(1)).getListBySearch(1,10,"name like '%youngs%'");
        verify(categoryService,times(1)).getPaginationBySearch(1,10,"name like '%youngs%'");
        verifyNoMoreInteractions(categoryService);
    }

    @Test
    @WithCustomMockUser
    public  void M_updateCategory_return200() throws  Exception{

        Category c=new Category();
        c.setId(0);
        c.setName("name");
        c.setCreateTime(new GregorianCalendar(2017,04,20).getTime());
        c.setUpdateTime(new GregorianCalendar(2017,04,20).getTime());
        c.setDeleted(false);


        Principal principal=(Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //when(categoryService.create(a, SecurityContextHolder.getContext())).thenReturn(true);
        when(categoryService.update(any(Category.class),any())).thenReturn(true);

        mockMVC.perform(post("/category/update").param("categoryId","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("no","100").param("level","0").param("sequence","0")
                .param("name","name").param("userId","1").param("createTime","2017/04/20")
                .param("updateTime","2017/04/20").param("noOfRead","0")
                .param("deleted","false")
        ).andDo(print()).andExpect(redirectedUrl("/category/list/1"));

        verify(categoryService,times(1)).update(any(Category.class),any());
        verifyNoMoreInteractions(categoryService);

    }
}
