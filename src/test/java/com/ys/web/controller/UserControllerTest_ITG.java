package com.ys.web.controller;

import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.security.factory.WithCustomMockUser;
import config.AppConfig_STG;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.*;

import static org.mockito.Matchers.any;
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
public class UserControllerTest_ITG {

    @Autowired
    UserController userController;

    @Resource
    private WebApplicationContext webApplicationContext;

    private  MockMvc mockMvc;

    private User user;

    @Before
    public  void setUp() {
        mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        user=new User();
        user.setEmail("");
        user.setPassword("");
        user.setRoleId(1);

    }

    @Test
    public  void A_home_throw404Exception() throws Exception {
        mockMvc.perform(get("/user/loginA")).andExpect(status().isNotFound());
    }

    @Test
    public  void B_home_return200() throws Exception {
        mockMvc.perform(get("/user/login")).andExpect(status().isOk()).andExpect(view().name("/user/user_login.jsp"));

    }

    @Test
    public  void C_getSignUp_return200() throws Exception {
        mockMvc.perform(get("/user/signup")).andExpect(status().isOk()).andExpect(view().name("/user/user_signUp.jsp"));

    }

    @Test
    public  void D_signUp_validationFailsForEmailEmpty() throws Exception {
        user.setPassword("password");
        mockMvc.perform(post("/user/signup")).andExpect(status().isOk()).andExpect(view().name("/user/user_signUp.jsp"));

    }

    @Test
    public  void E_signUp_validationFailsEmailNotCorrectFormat() throws Exception {
        user.setEmail("email.com");
        mockMvc.perform(post("/user/signup")).andExpect(status().isOk()).andExpect(view().name("/user/user_signUp.jsp"));

    }

    @Test
    public  void F_signUp_validationFailsForPasswordEmpty() throws Exception {
        user.setEmail("email@email.com");
        mockMvc.perform(post("/user/signup")).andExpect(status().isOk()).andExpect(view().name("/user/user_signUp.jsp"));
    }

    @Test
    @Transactional
    public  void G_signUp_return301RedirectSuccessPage() throws Exception {
        user.setEmail("test3@test.com");
        user.setUsername("username");
        user.setPassword("password");
        user.setPasswordConfirm("password");
        user.setRoleId(1);


        mockMvc.perform(post("/user/signup")
                .param("email",user.getEmail())
                .param("username",user.getUsername())
                .param("password",user.getPassword())
                .param("passwordConfirm",user.getPasswordConfirm())
                .param("roleId",String.valueOf(user.getRoleId()))

        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/user/welcome"));

    }

    @Test
    public  void H_getUpdate_return200() throws Exception {
        mockMvc.perform(get("/user/update")).andExpect(status().isOk()).andExpect(view().name("/user/user_update.jsp"));
    }


    @Test
    public  void I_update_validationFailsForPasswordEmpty() throws Exception {
        user.setEmail("email@email.com");
        user.setUsername("username");
        mockMvc.perform(post("/user/update").param("email",user.getEmail())
                .param("username",user.getUsername())
        ).andExpect(status().isOk()).andExpect(view().name("/user/user_update.jsp"));
    }

    @Test
    public  void J_update_validationFailsForEmail() throws Exception {

        user.setUsername("username");
        user.setPassword("password");
        mockMvc.perform(post("/user/update")
                .param("username",user.getUsername())
                .param("password",user.getPassword())
        ).andExpect(status().isOk()).andExpect(view().name("/user/user_update.jsp"));
    }

    @Test
    @WithCustomMockUser(username = "supermane",roleId = 3,id=394)
    public  void K_update_return301RedirectSuccessPage() throws Exception {


        user.setEmail("test1@test.com");
        user.setUsername("username");
        user.setPassword("password");
        user.setPasswordConfirm("password");
        user.setId(394);
        user.setRoleId(1);

        mockMvc.perform(post("/user/update")
                .param("id",String.valueOf(user.getId()))
                .param("email",user.getEmail())
                .param("username",user.getUsername())
                .param("password",user.getPassword())
                .param("passwordConfirm",user.getPasswordConfirm())
                .param("roleId",String.valueOf(user.getRoleId()))
        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));


    }

    @Test
    public  void L_getUpdatePassword_return200() throws Exception {
        mockMvc.perform(get("/user/updatePassword")).andExpect(status().isOk()).andExpect(view().name("/user/user_updatePassword.jsp"));
    }


    @Test
    public  void M_updatePassword_validationFailForPasswordNotMatch() throws Exception {
        mockMvc.perform(get("/user/updatePassword")).andExpect(status().isOk()).andExpect(view().name("/user/user_updatePassword.jsp"));
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


    @Test
    @WithCustomMockUser(roleId = 9,username = "super",id = 394)
    public  void L_updatePassword_return200RedirectToHome() throws Exception {

        user.setEmail("test1@test.com");
        user.setUsername("username");
        user.setPassword("password");
        user.setPasswordConfirm("password");
        user.setRoleId(1);
        user.setId(394);

        mockMvc.perform(post("/user/updatePassword")
                .param("oldPassword",user.getPassword())
                .param("password",user.getPassword())
                .param("passwordConfirm",user.getPasswordConfirm())
        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));

    }

    @Test
    public  void L_getList_return200RedirectToHome() throws Exception {

        List<User> userList= new ArrayList<User>();


        mockMvc.perform(get("/user/list")
        ).andExpect(status().isOk()).andExpect(model().attributeExists("userList"));

    }


    @Test
    public  void M_getListBySearch_return200RedirectToHome() throws Exception {

        List<User> userList= new ArrayList<>();

        mockMvc.perform(post("/user/search")
                .param("key","username").param("value","ys u")
        ).andDo(print()).andExpect(status().isOk()).andExpect(model().attributeExists("userList"));

    }

}
