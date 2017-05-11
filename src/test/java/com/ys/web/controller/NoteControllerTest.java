package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.Note;
import com.ys.app.model.User;
import com.ys.app.security.factory.WithCustomMockUser;
import com.ys.app.security.util.UtilSecurityContextTest;
import com.ys.app.service.NoteService;
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

import java.security.Principal;
import java.util.GregorianCalendar;

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
public class NoteControllerTest {

    @Mock
    NoteService noteService;


    @InjectMocks
    NoteController noteController;

    private MockMvc mockMVC;

    @Before
    public  void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMVC= MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    public  void A_home_Thorw404Exception() throws Exception {
        mockMVC.perform(get("/note/listA")).andExpect(status().isNotFound());
        verifyNoMoreInteractions(noteService);
    }

    @Test
    public  void B_home_throwCustomException() {
        try {
            mockMVC.perform(get("/note/list/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
        verifyNoMoreInteractions(noteService);
    }

    @Test
    @WithCustomMockUser(username = "youngsam",id = 1,roleId = 1)
    public  void C_home_Return200() throws Exception {
            mockMVC.perform(get("/note/list/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
            .andExpect(view().name("/note/note_list"));
            verify(noteService,times(1)).getList(1,1,10);
            verify(noteService,times(1)).getPagination(1,1,10);

        verifyNoMoreInteractions(noteService);
    }

    @Test
    @WithCustomMockUser(username = "youngsam",id = 1,roleId = 1)
    public  void D_home_ForwardToListingPage() throws Exception{

        mockMVC.perform(get("/note/list")).andExpect(status().isOk())
                .andExpect(view().name("/note/note_list"));
        verify(noteService,times(1)).getList(1,1,10);
        verify(noteService,times(1)).getPagination(1,1,10);

        verifyNoMoreInteractions(noteService);
    }

    @Test
    public  void E_read_ThrowCustomException() throws Exception {

        try {
            mockMVC.perform(get("/note/read/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }

        verifyNoMoreInteractions(noteService);
    }

    @Test
    @WithCustomMockUser(username = "youngsam",id = 1,roleId = 1)
    public  void F_read_return200() throws Exception {
        User user =new User();
        user.setId(1);
        user.setRoleId(1);
        Principal principal= UtilSecurityContextTest.returnPrincipal(user, 1);
        when(noteService.read(any(),any(),any(Principal.class))).thenReturn(new Note());
        mockMVC.perform(get("/note/read/431")).andExpect(status().isOk())
                .andExpect(view().name("/note/note_read"));
        verify(noteService,times(1)).read(any(),any(),any(Principal.class));
        verifyNoMoreInteractions(noteService);
    }


    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public  void G_delete_throwCustomException() throws Exception {
        try {
            mockMVC.perform(post("/note/delete/-1"));
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(CustomException.class);
        }
        verifyNoMoreInteractions(noteService);
    }

    @Test
    @WithCustomMockUser(username = "youngsam",id = 1,roleId = 1)
    public  void H_delete_return200() throws Exception {
        User user =new User();
        user.setId(1);
        user.setRoleId(1);
        Principal principal= UtilSecurityContextTest.returnPrincipal(user, 1);
        when(noteService.delete(any(),any(),any(Principal.class))).thenReturn(true);
        mockMVC.perform(post("/note/delete/431")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/note/list/1"));
        verify(noteService,times(1)).delete(any(),any(),any(Principal.class));
        verifyNoMoreInteractions(noteService);
    }
    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public  void H_getCreate_returnCreateJspPage() throws  Exception{

        mockMVC.perform(get("/note/create")).andExpect(view().name("/note/note_create"));

        verifyNoMoreInteractions(noteService);
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


        verifyNoMoreInteractions(noteService);
    }


    @Test
    @WithCustomMockUser(id = 354,roleId = 1,username ="youngsam" )
    public void K_create_return200AndListPage() throws Exception{

        when(noteService.create(any(),any(),any(Principal.class))).thenReturn(true);

        mockMVC.perform(post("/note/create").param("noteId","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("msg","msg").param("sendId","1").param("recvId","2")
                .param("updateTime","2017/04/20").param("noOfRead","0")
                .param("deleted","false")
        ).andDo(print()).andExpect(redirectedUrl("/note/list/1"));

        verify(noteService,times(1)).create(any(),any(),any(Principal.class));
        verifyNoMoreInteractions(noteService);
    }


}
