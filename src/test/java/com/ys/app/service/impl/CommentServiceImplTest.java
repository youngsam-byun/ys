package com.ys.app.service.impl;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.ys.app.model.Comment;
import com.ys.app.model.User;
import com.ys.app.model.dto.CommentDTO;
import com.ys.app.repo.CommentRepository;
import com.ys.app.repo.UserRepository;
import com.ys.app.service.CommentService;
import com.ys.app.util.TestDoubles;
import com.ys.app.util.UtilPagination;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

import static com.ys.app.security.util.UtilSecurityContextTest.returnAuthentication;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


/**
 * Created by byun.ys on 4/13/2017.
 */


@RunWith(HierarchicalContextRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentServiceImplTest {

    CommentService commentService;


    CommentRepository commentRepository;

    UserRepository userRepository;


    @Before
    public void init() {

        commentRepository = TestDoubles.mock(CommentRepository.class);
        userRepository = TestDoubles.dummy(UserRepository.class);
        commentService = new CommentServiceImpl(commentRepository, userRepository);

    }

    @Test(expected = NullPointerException.class)
    public void A_writeComment_throwNullPointerException() {

        commentService.create(null, null);
        verify(commentRepository, times(0)).create(null);
        verifyNoMoreInteractions(commentRepository);

    }

    @Test(expected = AccessDeniedException.class)
    public void B_writeComment_throwAccessDeniedException() {

        Comment actual = new Comment();
        Authentication authentication = returnAuthentication(new User(), 0);
        commentService.create(actual, authentication);
        verify(commentRepository, times(0)).create(null);
        verifyNoMoreInteractions(commentRepository);
    }

    @Test(expected = NullPointerException.class)
    public void C_readComment_throwNullPointerException() {
        commentService.read(null);
        verify(commentRepository, times(0)).read(0);
        verifyNoMoreInteractions(commentRepository);
    }


    @Test(expected = NullPointerException.class)
    public void D_updateComment_shouldThrowNullPointerException() {

        commentService.update(null, null);
        verify(commentRepository, times(0)).update(null);
        verifyNoMoreInteractions(commentRepository);

    }

    @Test(expected = AccessDeniedException.class)
    public void E_updateComment_throwAccessDeniedException() {

        User user = new User();
        user.setId(1);
        Comment actual = new Comment();
        Authentication authentication = returnAuthentication(user, 0);
        commentService.update(actual, authentication);
        verify(commentRepository, times(0)).update(null);
        verifyNoMoreInteractions(commentRepository);
    }


    @Test(expected = NullPointerException.class)
    public void G_deleteComment_throwNullPointerException() {

        commentService.delete(null, null);
        verify(commentRepository, times(0)).delete(0);
        verifyNoMoreInteractions(commentRepository);
    }

    @Test(expected = NullPointerException.class)
    public void I_getList_shouldThrowNullPointerException() {

        commentService.getList(null, null);
        verify(commentRepository, times(0)).getList(0, 0);
        verifyNoMoreInteractions(commentRepository);

    }


    @Test(expected = NullPointerException.class)
    public void J_getPageNation_shouldThrowNullPointerException() {

        commentService.getPagination(null, null);
        verify(commentRepository, times(0)).getTotal();
        verifyNoMoreInteractions(commentRepository);
    }


    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class ObjectFound {

        User user;
        Comment mockedComment;
        List<Comment> mockedCommentList = new ArrayList<>();

        @Before
        public void init() {

            mockedComment = createComment();

        }

        private Comment createComment() {
            Comment comment = new Comment();
            comment.setId(2);
            comment.setUserId(1);
            return comment;
        }

        @Test
        public void A_writeComment_insertAndReturnTrue() {
            when(commentRepository.create(mockedComment)).thenReturn(1);

            Authentication authentication = returnAuthentication(new User(), 1);
            boolean b = commentService.create(mockedComment, authentication);
            assertThat(b).isTrue();
            verify(commentRepository, times(1)).create(mockedComment);
            verifyNoMoreInteractions(commentRepository);
        }

        @Test
        public void B_readComment_returnComment() {
            when(commentRepository.read(1)).thenReturn(mockedComment);

            CommentDTO commentDTO = commentService.read(1);
            Comment actual = commentDTO.getComment();
            assertThat(actual).isEqualTo(mockedComment);
            verify(commentRepository, times(1)).read(1);
            verifyNoMoreInteractions(commentRepository);
        }

        @Test
        public void C_updateComment_returnTrue() {
            when(commentRepository.update(mockedComment)).thenReturn(1);

            Authentication authentication = returnAuthentication(new User(), 9);
            boolean actual = commentService.update(mockedComment, authentication);
            assertThat(actual).isEqualTo(true);
            verify(commentRepository, times(1)).update(mockedComment);
            verifyNoMoreInteractions(commentRepository);
        }

        @Test
        public void D_deleteComment_returnTrue() {
            when(commentRepository.read(1)).thenReturn(mockedComment);
            when(commentRepository.delete(1)).thenReturn(1);


            User user = new User();
            user.setId(1);
            Authentication authentication = returnAuthentication(user, 9);
            boolean b = commentService.delete(1, authentication);
            assertThat(b).isTrue();
            verify(commentRepository, times(1)).read(1);
            verify(commentRepository, times(1)).delete(1);
            verifyNoMoreInteractions(commentRepository);
        }

        @Test
        public void E_getListComment_returnListArray() throws InvalidArgumentException {
            when(commentRepository.getList(1, 10)).thenReturn(mockedCommentList);

            List<CommentDTO> commentDTOList = commentService.getList(1, 15);
            assertThat(commentDTOList).isEqualTo(mockedCommentList);
            verify(commentRepository, times(1)).getList(1, 15);
            verifyNoMoreInteractions(commentRepository);
        }


        @Test
        public void G_getPagination_returnUtilPageNation() {
            when(commentRepository.getTotal()).thenReturn(100);

            UtilPagination up = commentService.getPagination(1, 10);
            assertThat(up).isNotNull();
            verify(commentRepository, times(1)).getTotal();
            verifyNoMoreInteractions(commentRepository);
        }


    }

}

