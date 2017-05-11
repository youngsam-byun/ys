package com.ys.app.service.impl;

import com.ys.app.model.Note;
import com.ys.app.model.User;
import com.ys.app.model.dto.NoteDTO;
import com.ys.app.repo.NoteRepository;
import com.ys.app.repo.UserRepository;
import com.ys.app.security.util.UtilSecurityContextTest;
import com.ys.app.service.NoteService;
import com.ys.app.util.TestDoubles;
import com.ys.app.util.UtilPagination;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.security.access.AccessDeniedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by byun.ys on 4/22/2017.
 */

@RunWith(HierarchicalContextRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NoteServiceImplTest {

    NoteRepository noteRepository;
    UserRepository userRepository;
    NoteService noteService;

    @Before
    public void init() {
        noteRepository = TestDoubles.mock(NoteRepository.class);
        userRepository = TestDoubles.mock(UserRepository.class);
        noteService = new NoteServiceImpl(noteRepository, userRepository);
    }


    @Test(expected = NullPointerException.class)
    public void A_create_throwNullPointerException() {
        noteService.create(null,null, null);
    }

    @Test(expected = AccessDeniedException.class)
    public void B_create_throwAccessDeniedException() {
        Note c = new Note();
        noteService.create(1,c, UtilSecurityContextTest.returnPrincipal(new User(), 0));
        verify(noteRepository, times(0)).create(c);
        Mockito.verifyNoMoreInteractions(noteRepository);
    }



    @Test(expected = NullPointerException.class)
    public void D_read_throwNullPointerException() {

        noteService.read(null, null,null);
        verify(noteRepository, times(0)).read(0);
        Mockito.verifyNoMoreInteractions(noteRepository);
    }


    @Test(expected = NullPointerException.class)
    public void E1_delete_throwNullPointerException() {
        noteService.delete(null, null,null);
        verify(noteRepository, times(0)).deleteByUpdateSendDel(0);
        verifyNoMoreInteractions(noteRepository);
    }

    @Test(expected = AccessDeniedException.class)
    public void E2_delete_throwAccessDeniedException() {
        noteService.delete(1, 1,UtilSecurityContextTest.returnPrincipal(new User(), 0));
        verify(noteRepository, times(0)).deleteByUpdateSendDel(0);
        verifyNoMoreInteractions(noteRepository);
    }

    @Test(expected = NullPointerException.class)
    public void F_getList_throwNullPointerException() {
        noteService.getList(null, null,null);
        verify(noteRepository, times(0)).getList(0, 0);
        verifyNoMoreInteractions(noteRepository);
    }


    @Test(expected = NullPointerException.class)
    public void H_getPagination_throwNullPointerException() {
        noteService.getPagination(null,null, null);
        verify(noteRepository, times(0)).getTotal();
        verifyNoMoreInteractions(noteRepository);
    }


    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class ObjectFound {
        Note created;
        List<Note> createdList;
        User user;

        @Before
        public void setUp() {
            created = createNote();
            createdList = createdNoteList();
            user = new User();
            user.setId(1);
            user.setRoleId(1);
            user.setUsername("username");
        }

        private Note createNote() {
            Note n = new Note();
            n.setId(1);
            n.setMsg("msg");
            n.setSendId(1);
            n.setRecvId(2);
            n.setSendTime(new Date());
            return n;
        }

        private List<Note> createdNoteList() {
            List<Note> noteList = new ArrayList<>();
            noteList.add(created);
            return noteList;
        }

        @Test
        public void A_create_returnTrue() {

            when(noteRepository.create(created)).thenReturn(1);

            boolean b = noteService.create(1, created, UtilSecurityContextTest.returnPrincipal(user, 1));
            assertThat(b).isTrue();
            verify(noteRepository, times(1)).setTable(1);
            verify(noteRepository, times(1)).create(created);
            verifyNoMoreInteractions(noteRepository);
        }

        @Test
        public  void B_read_returnObject(){
            when(noteRepository.read(1)).thenReturn(created);

            Note returned=noteService.read(1, 1,UtilSecurityContextTest.returnPrincipal(user, 1));
            assertThat(returned).isEqualTo(created);
            verify(noteRepository, times(1)).setTable(1);
            verify(noteRepository,times(1)).read(1);
            verifyNoMoreInteractions(noteRepository);
        }


        @Test
        public  void D_delete_returnTrue(){

            when(noteRepository.read(1)).thenReturn(created);
            when(noteRepository.deleteByUpdateSendDel(1)).thenReturn(1);

            noteService.delete(1,1,UtilSecurityContextTest.returnPrincipal(user, 1));

            verify(noteRepository, times(1)).setTable(1);
            verify(noteRepository,times(1)).read(1);
            verify(noteRepository,times(1)).deleteByUpdateSendDel(1);
            verifyNoMoreInteractions(noteRepository);
        }

        @Test
        public  void E_getList_returnListArray()  {

            when(noteRepository.getList(1,10)).thenReturn(createdList);

            List<NoteDTO> nl=noteService.getList(1,1,10);

            verify(noteRepository, times(1)).setTable(1);
            verify(noteRepository,times(1)).getList(1,10);
            verifyNoMoreInteractions(noteRepository);
        }



        @Test
        public  void G_getPagination_returnUtilPageNation(){
            when(noteRepository.getTotal()).thenReturn(100);

            UtilPagination up= noteService.getPagination(1,1,10);
            assertThat(up).isNotNull();
            verify(noteRepository, times(1)).setTable(1);
            verify(noteRepository,times(1)).getTotal();
            verifyNoMoreInteractions(noteRepository);
        }

    }
}
