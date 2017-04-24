package com.ys.app.service.impl;

import com.ys.app.model.User;
import com.ys.app.repo.UserRepository;
import com.ys.app.security.UtilSecurityContextTest;
import com.ys.app.service.UserService;
import com.ys.app.util.TestDoubles;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ys.app.security.UtilSecurityContextTest.returnSecurityContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


/**
 * Created by byun.ys on 4/13/2017.
 */


@RunWith(HierarchicalContextRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {

    UserService userService;

    UserRepository userRepository;


    @Before
    public void init() {

        userRepository = TestDoubles.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);

    }

    @Test(expected = NullPointerException.class)
    public void A_createUser_throwNullPointerException() {

        userService.createUser(null, null);
        verifyNoMoreInteractions(userRepository);

    }

    @Test(expected = AccessDeniedException.class)
    public void B_createUser_throwAccessDeniedException() {

        User actual = new User();
        SecurityContext securityContext = returnSecurityContext(new User(),1);
        userService.createUser(actual, securityContext);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void C_createUser_returnTrue() {
        User actual = new User();
        SecurityContext securityContext = returnSecurityContext(new User(),9);
        userService.createUser(actual, securityContext);
        verify(userRepository,times(1)).create(actual);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = NullPointerException.class)
    public void D_readByEmail_throwNullPointerException() {

        userService.readByEmail(null);
        verifyNoMoreInteractions(userRepository);
    }


    @Test(expected = NullPointerException.class)
    public  void E_readUser_throwNullPointerException() {
        userService.read(null);
        verifyNoMoreInteractions(userRepository);
    }


    @Test(expected = NullPointerException.class)
    public  void E_updateUser_throwNullPointerException() {
        userService.updateUser(null,null);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = AccessDeniedException.class)
    public  void F_updateUser_throwAccessDeniedException() {
        User user = new User();
        user.setId(9);

        User user2=new User();
        user2.setId(0);
        userService.updateUser(user,UtilSecurityContextTest.returnSecurityContext(user2,0));

        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = NullPointerException.class)
    public  void G_updatePassword_throwNullPointerException() {
        userService.updatePassword(null,null);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = AccessDeniedException.class)
    public  void H_updatePassword_throwAccessDeniedException() {
        User user = new User();
        user.setId(9);

        User user2=new User();
        user2.setId(0);
        userService.updatePassword(user,UtilSecurityContextTest.returnSecurityContext(user2,0));

        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = NullPointerException.class)
    public  void H_delete_throwNullPointerException() {
        userService.deleteUser(null,null);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = AccessDeniedException.class)
    public  void I_delete_throwAccessDeniedException() {
        User user = new User();
        user.setId(9);

        User user2=new User();
        user2.setId(0);
        userService.deleteUser(9,UtilSecurityContextTest.returnSecurityContext(user2,0));

        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = NullPointerException.class)
    public  void J_getList_throwNullPointerException() {
        userService.getList(null,null);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = NullPointerException.class)
    public  void K_getListBySearch_throwNullPointerException() {
        userService.getListBySearch(null,null,null);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = NullPointerException.class)
    public  void L_getPagination_throwNullPointerException() {
        userService.getPagination(null,null);
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = NullPointerException.class)
    public  void M_getListBySearch_throwNullPointerException() {
        userService.getPaginationBySearch(null,null,null);
        verifyNoMoreInteractions(userRepository);
    }

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class ObjectFound {

        User mockedUser;
        List<User> mockedUserList = new ArrayList<>();

        @Before
        public void init() {

            mockedUser = createUser();

        }

        private User createUser() {
            User user = new User();
            user.setId(2);

            return user;
        }

        @Test
        public  void A_createUser_returnTrue(){

            when(userRepository.create(mockedUser)).thenReturn(1);

            assertThat(userService.createUser(mockedUser, UtilSecurityContextTest.returnSecurityContext(mockedUser,9))).isTrue();
            verify(userRepository,times(1)).create(mockedUser);
            verifyNoMoreInteractions(userRepository);
        }

        @Test
        public  void B_read_returnUser() {
            when(userRepository.read(1)).thenReturn(mockedUser);

            assertThat(userService.read(1)).isEqualTo(mockedUser);
            verify(userRepository,times(1)).read(1);
            verifyNoMoreInteractions(userRepository);
        }

        @Test
        public  void C_readByEmail_returnUser() {

            when(userRepository.readByEmail("hah@email.com")).thenReturn(mockedUser);

            assertThat(userService.readByEmail("hah@email.com")).isEqualTo(mockedUser);
            verify(userRepository,times(1)).readByEmail("hah@email.com");
            verifyNoMoreInteractions(userRepository);
        }

        @Test
        public  void D_updateUser_returnTrue() {

            when(userRepository.update(mockedUser)).thenReturn(1);

            assertThat(userService.updateUser(mockedUser,UtilSecurityContextTest.returnSecurityContext(mockedUser,9))).isTrue();
            verify(userRepository,times(1)).update(mockedUser);
            verifyNoMoreInteractions(userRepository);
        }
        @Test
        public  void E_updatePassword_returnTrue() {

            when(userRepository.updatePassword(mockedUser)).thenReturn(1);

            assertThat(userService.updatePassword(mockedUser,UtilSecurityContextTest.returnSecurityContext(mockedUser,9))).isTrue();
            verify(userRepository,times(1)).updatePassword(mockedUser);
            verifyNoMoreInteractions(userRepository);
        }
        @Test
        public  void E_updateTrialByOne_returnTrue() {

            when(userRepository.updateTrialCountByOne("hah@email.com",new Date())).thenReturn(1);

            assertThat(userService.updateTrialCountByOne("hah@email.com")).isTrue();
            verify(userRepository,times(1)).updateTrialCountByOne("hah@email.com",new Date());
            verifyNoMoreInteractions(userRepository);
        }
        @Test
        public  void F_deleteUser_returnTrue() {

            when(userRepository.delete(1)).thenReturn(1);
            assertThat(userService.deleteUser(1,UtilSecurityContextTest.returnSecurityContext(mockedUser,9))).isTrue();
            verify(userRepository,times(1)).delete(1);
            verifyNoMoreInteractions(userRepository);
        }


        @Test
        public  void G_getList_returnUserList() {

            when(userRepository.getList(1, 10)).thenReturn(mockedUserList);

            assertThat(userService.getList(1,10)).isEqualTo(mockedUserList);
            verify(userRepository,times(1)).getList(1,10);
            verifyNoMoreInteractions(userRepository);
        }

        @Test
        public  void H_getList_returnUserList() {


            when(userRepository.getListBySearch(1, 10, "haha")).thenReturn(mockedUserList);


            assertThat(userService.getListBySearch(1,10,"haha")).isEqualTo(mockedUserList);
            verify(userRepository,times(1)).getListBySearch(1,10,"haha");
            verifyNoMoreInteractions(userRepository);
        }
    }

}

