package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.User;
import com.ys.app.security.factory.WithCustomMockUser;
import com.ys.app.security.util.UtilSecurityContextTest;
import config.AppConfig_STG;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.security.Principal;

import static org.assertj.core.api.Assertions.assertThat;
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
public class NoteControllerTest_ITG {


    @Autowired
    NoteController noteController;

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMVC;

    @Before
    public  void setUp(){
        mockMVC= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public  void A_home_Thorw404Exception() throws Exception {
        mockMVC.perform(get("/note/listA")).andExpect(status().isNotFound());
    }

    @Test
    public  void B_home_throwCustomException() {
        try {
            mockMVC.perform(get("/note/list/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
    }

    @Test
    @WithCustomMockUser(username = "youngsam",id = 1,roleId = 1)
    public  void C_home_Return200() throws Exception {
            mockMVC.perform(get("/note/list/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
            .andExpect(view().name("/note/note_list"));

    }

    @Test
    @WithCustomMockUser(username = "youngsam",id = 1,roleId = 1)
    public  void D_home_ForwardToListingPage() throws Exception{

        mockMVC.perform(get("/note/list")).andExpect(status().isOk())
                .andExpect(view().name("/note/note_list"));

    }

    @Test
    public  void E_read_ThrowCustomException() throws Exception {

        try {
            mockMVC.perform(get("/note/read/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }

    }

    @Test
    @WithCustomMockUser(username = "youngsam",id = 2,roleId = 9)
    public  void F_read_return200() throws Exception {

        mockMVC.perform(get("/note/read/1")).andExpect(status().isOk())
                .andExpect(view().name("/note/note_read"));

    }


    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public  void G_delete_throwCustomException() throws Exception {
        try {
            mockMVC.perform(post("/note/delete/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }

    }

    @Test
    @WithCustomMockUser(username = "youngsam",id = 1,roleId = 1)
    public  void H_delete_return200() throws Exception {
        User user =new User();
        user.setId(1);
        user.setRoleId(1);
        Principal principal= UtilSecurityContextTest.returnPrincipal(user, 1);

        mockMVC.perform(post("/note/delete/1")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/note/list/1"));
    }
    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public  void H_getCreate_returnCreateJspPage() throws  Exception{

        mockMVC.perform(get("/note/create")).andExpect(view().name("/note/note_create"));


    }

//

    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public void J_create_returnValidationFailsForbody() throws Exception{

        mockMVC.perform(post("/note/create").param("noteId","1")
                .param("no","100").param("level","0").param("sequence","0").param("title","ddddd")
                .param("name","").param("userId","1").param("createTime","20/04/2017")
                .param("updateTime","20/04/2017").param("noOfRead","0")
                .param("deleted","false")
        ).andExpect(view().name("/note/note_create"));


    }


    @Test
    @WithCustomMockUser(id = 2,roleId = 9,username ="youngsam" )
    public void K_create_return200AndListPage() throws Exception{


        mockMVC.perform(post("/note/create").param("noteId","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("msg","msg").param("sendId","1").param("recvId","2")
                .param("updateTime","2017/04/20").param("noOfRead","0")
                .param("deleted","false")
        ).andDo(print()).andExpect(redirectedUrl("/note/list/1"));

    }


}
