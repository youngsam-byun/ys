package com.ys.web.controller;

import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.model.validator.PasswordUpdateFormValidator;
import com.ys.app.security.factory.WithCustomMockUser;
import com.ys.app.security.factory.WithCustomUserDetailsSecurityContextFactory;
import com.ys.app.security.service.CustomUserDetailsService;
import com.ys.app.service.UserService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by byun.ys on 4/18/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig_CONTROLLER_TEST.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

    @Mock
    UserService userService;


    PasswordUpdateFormValidator passwordUpdateValidator;

    @InjectMocks
    UserController userController;

    private MockMvc mockMVC;

    private User user;

    @Before
    public  void setUp(){
        passwordUpdateValidator=new PasswordUpdateFormValidator();
        userController=new UserController(userService,passwordUpdateValidator);
        MockitoAnnotations.initMocks(this);
                mockMVC= MockMvcBuilders.standaloneSetup(userController).build();

        user=new User();
        user.setEmail("");
        user.setPassword("");
        user.setRoleId(1);


    }

    @Test
    public  void A_home_throw404Exception() throws Exception {
        mockMVC.perform(get("/user/loginA")).andExpect(status().isNotFound());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public  void B_home_return200() throws Exception {
        mockMVC.perform(get("/user/login")).andExpect(status().isOk()).andExpect(view().name("/user/user_login.jsp"));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public  void C_getSignUp_return200() throws Exception {
        mockMVC.perform(get("/user/signup")).andExpect(status().isOk()).andExpect(view().name("/user/user_signUp.jsp"));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public  void D_signUp_validationFailsForEmailEmpty() throws Exception {
        user.setPassword("password");
        mockMVC.perform(post("/user/signup")).andExpect(status().isOk()).andExpect(view().name("/user/user_signUp.jsp"));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public  void E_signUp_validationFailsEmailNotCorrectFormat() throws Exception {
        user.setEmail("email.com");
        mockMVC.perform(post("/user/signup")).andExpect(status().isOk()).andExpect(view().name("/user/user_signUp.jsp"));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public  void F_signUp_validationFailsForPasswordEmpty() throws Exception {
        user.setEmail("email@email.com");
        mockMVC.perform(post("/user/signup")).andExpect(status().isOk()).andExpect(view().name("/user/user_signUp.jsp"));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public  void G_signUp_return301RedirectSuccessPage() throws Exception {
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setUsername("username");
        user.setPasswordConfirm("password");

        when(userService.createUser(any(User.class),any(Authentication.class))).thenReturn(true);


        mockMVC.perform(post("/user/signup")
        .param("email",user.getEmail())
                .param("username",user.getUsername())
        .param("password",user.getPassword())
                        .param("passwordConfirm",user.getPasswordConfirm())
        .param("roleId",String.valueOf(user.getRoleId()))

        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/user/welcome"));

        verify(userService,times(1)).createUser(any(User.class),any(Authentication.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public  void H_getUpdate_return200() throws Exception {
        mockMVC.perform(get("/user/update")).andExpect(status().isOk()).andExpect(view().name("/user/user_update.jsp"));
        verifyNoMoreInteractions(userService);
    }


    @Test
    public  void I_update_validationFailsForPasswordEmpty() throws Exception {
        user.setEmail("email@email.com");
        user.setUsername("username");
        mockMVC.perform(post("/user/update").param("email",user.getEmail())
        .param("username",user.getUsername())
        ).andExpect(status().isOk()).andExpect(view().name("/user/user_update.jsp"));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public  void J_update_validationFailsForEmail() throws Exception {

        user.setUsername("username");
        user.setPassword("password");
        mockMVC.perform(post("/user/update")
        .param("username",user.getUsername())
        .param("password",user.getPassword())
        ).andExpect(status().isOk()).andExpect(view().name("/user/user_update.jsp"));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public  void K_update_return301RedirectSuccessPage() throws Exception {

        when(userService.updateUser(any(User.class),any(Authentication.class))).thenReturn(true);

        user.setEmail("email@email.com");
        user.setPassword("password");
        user.setPasswordConfirm("password");
        user.setUsername("username");
        user.setRoleId(1);

        mockMVC.perform(post("/user/update")
                .param("email",user.getEmail())
                .param("username",user.getUsername())
                .param("password",user.getPassword())
                .param("passwordConfirm",user.getPasswordConfirm())
                .param("roleId",String.valueOf(user.getRoleId()))
        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));

        verify(userService,times(1)).updateUser(any(User.class),any(Authentication.class));
        verifyNoMoreInteractions(userService);

    }

    @Test
    public  void L_getUpdatePassword_return200() throws Exception {
        mockMVC.perform(get("/user/updatePassword")).andExpect(status().isOk()).andExpect(view().name("/user/user_updatePassword.jsp"));
        verifyNoMoreInteractions(userService);
    }


    @Test
    public  void M_updatePassword_validationFailForPasswordNotMatch() throws Exception {
        mockMVC.perform(get("/user/updatePassword")).andExpect(status().isOk()).andExpect(view().name("/user/user_updatePassword.jsp"));
        verifyNoMoreInteractions(userService);
    }

    public static class MockSecurityContext implements SecurityContext {

        private static final long serialVersionUID = -1386535243513362694L;

        private Authentication authentication;

        public MockSecurityContext(Authentication authentication) {
            this.authentication = authentication;
        }

        @Override
        public Authentication getAuthentication() {
            return this.authentication;
        }

        @Override
        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Integer roleId=user.getRoleId();
        Set<GrantedAuthority> authorities = new HashSet<>();

        for(Role role:Role.values()){
            if(roleId.equals(role.getId())){
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
                break;
            }
        }
        return authorities;
    }


    //youngsam needs to test how to inject mock securityContext and mock Authentication
    @Test
    @WithCustomMockUser
    public  void L_updatePassword_return200RedirectToHome() throws Exception {

        when(userService.updatePassword(any(User.class),any(Authentication.class))).thenReturn(true);

        user.setEmail("email@email.com");
        user.setPassword("password");
        user.setPasswordConfirm("password");
        user.setUsername("username");
        user.setRoleId(1);


        mockMVC.perform(post("/user/updatePassword")
                .param("oldPassword",user.getPassword())
                .param("password",user.getPassword())
                .param("passwordConfirm",user.getPasswordConfirm())
        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));

        verify(userService,times(1)).updatePassword(any(User.class),any(Authentication.class));
        verifyNoMoreInteractions(userService);
    }


//    @Test
//    public  void L_getResetPassword_return200() throws Exception {
//        mockMVC.perform(get("/user/resetPassword")).andExpect(status().isOk()).andExpect(view().name("/user/user_resetPassword.jsp"));
//        verifyNoMoreInteractions(userService);
//    }
//
//
//    @Test
//    public  void M_resetPassword_throwValidationException() throws Exception {
//
//
//        mockMVC.perform(post("/user/resetPassword")
//                .param("oldPassword","")
//                .param("password","b")
//                .param("passwordConfirm","c")
//        ).andExpect(view().name("/user/user_resetPassword.jsp"));
//
//        verifyNoMoreInteractions(userService);
//    }

//
//
//    @Test
//    public  void G_delete_throwCustomException() throws Exception {
//        try {
//            mockMVC.perform(post("/user/delete/-1"));
//        } catch (Exception e) {
//            assertThat(e).hasCauseInstanceOf(CustomException.class);
//        }
//    }
//
//    @Test
//    public  void H_get_write_returnWriteJspPage() throws  Exception{
//
//        mockMVC.perform(get("/user/write")).andExpect(view().name("/user/write.jsp"));
//    }
//
//    @Test
//    public void I_write_returnValidationFailsFortitle() throws Exception{
//
//        mockMVC.perform(post("/user/write").param("categoryId","1")
//        .param("no","100").param("level","0").param("sequence","0").param("title","")
//                .param("body","body").param("userId","1").param("createTime","20/04/2017")
//                        .param("updateTime","20/04/2017").param("noOfRead","0")
//                .param("deleted","false")
//        ).andExpect(view().name("/user/write.jsp"));
//
//    }
//
//
//    @Test
//    public void J_write_returnValidationFailsForbody() throws Exception{
//
//        mockMVC.perform(post("/user/write").param("categoryId","1")
//                .param("no","100").param("level","0").param("sequence","0").param("title","ddddd")
//                .param("body","").param("userId","1").param("createTime","20/04/2017")
//                .param("updateTime","20/04/2017").param("noOfRead","0")
//                .param("deleted","false")
//        ).andExpect(view().name("/user/write.jsp"));
//
//
//
//    }


//
//    @Test
//    public void K_write_return200AndListPage() throws Exception{
//
//        User a=new User();
//        a.setId(0);
//        a.setCategoryId(1);
//        a.setTitle("ttt");
//        a.setBody("body");
//        a.setUserId(1);
//        a.setCreateTime(new GregorianCalendar(2017,04,20).getTime());
//        a.setUpdateTime(new GregorianCalendar(2017,04,20).getTime());
//        a.setNoOfRead(0);
//        a.setDeleted(false);
//
//
//
//        //when(userService.writeUser(a, SecurityContextHolder.getContext())).thenReturn(true);
//        when(userService.writeUser(any(),any())).thenReturn(true);
//
//        mockMVC.perform(post("/user/write").param("categoryId","1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//                .param("no","100").param("level","0").param("sequence","0").param("title","ttt")
//                .param("body","body").param("userId","1").param("createTime","2017/04/20")
//                .param("updateTime","2017/04/20").param("noOfRead","0")
//                .param("deleted","false")
//        ).andDo(print()).andExpect(view().name("/user/list.jsp"));
//
//        verify(userService,times(1)).writeUser(any(),any());
//    }
}
