package com.ys.app.service.impl;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.ys.app.model.Article;
import com.ys.app.model.User;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.repo.ArticleRepository;
import com.ys.app.repo.UserRepository;
import com.ys.app.service.ArticleService;
import com.ys.app.util.TestDoubles;
import com.ys.app.util.UtilPagination;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.ys.app.security.UtilSecurityContextTest.returnSecurityContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


/**
 * Created by byun.ys on 4/13/2017.
 */


@RunWith(HierarchicalContextRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleServiceImplTest {

    ArticleService articleService;


    ArticleRepository articleRepository;

    UserRepository userRepository;


    @Before
    public void init() {

        articleRepository = TestDoubles.mock(ArticleRepository.class);
        userRepository=TestDoubles.dummy(UserRepository.class);
        articleService = new ArticleServiceImpl(articleRepository,userRepository);

    }

    @Test(expected = NullPointerException.class)
    public void A_writeArticle_throwNullPointerException() {

        articleService.writeArticle(null, null);
        verify(articleRepository,times(0)).create(null);
        verifyNoMoreInteractions(articleRepository);

    }

    @Test(expected = AccessDeniedException.class)
    public void B_writeArticle_throwAccessDeniedException() {

        Article actual = new Article();
        SecurityContext securityContext = returnSecurityContext(new User(),0);
        articleService.writeArticle(actual, securityContext);
        verify(articleRepository,times(0)).create(null);
        verifyNoMoreInteractions(articleRepository);
    }

    @Test(expected = NullPointerException.class)
    public  void C_readArticle_throwNullPointerException() {
        articleService.readArticle(null);
        verify(articleRepository,times(0)).read(0);
        verifyNoMoreInteractions(articleRepository);
    }


    @Test(expected = NullPointerException.class)
    public void D_updateArticle_shouldThrowNullPointerException() {

        articleService.updateArticle(null, null);
        verify(articleRepository,times(0)).update(null);
        verifyNoMoreInteractions(articleRepository);

    }

    @Test(expected = AccessDeniedException.class)
    public void E_updateArticle_throwAccessDeniedException() {

        User user=new User();
        user.setId(1);
        Article actual = new Article();
        SecurityContext securityContext = returnSecurityContext(user,0);
        articleService.updateArticle(actual, securityContext);
        verify(articleRepository,times(0)).update(null);
        verifyNoMoreInteractions(articleRepository);
    }


    @Test(expected = NullPointerException.class)
    public void G_deleteArticle_throwNullPointerException(){

        articleService.deleteArticle(null,null);
        verify(articleRepository,times(0)).delete(0);
        verifyNoMoreInteractions(articleRepository);
    }

    @Test(expected = NullPointerException.class)
    public void I_getList_shouldThrowNullPointerException() {

        articleService.getList(null,null);
        verify(articleRepository,times(0)).getList(0,0);
        verifyNoMoreInteractions(articleRepository);

    }


    @Test(expected = NullPointerException.class)
    public void J_getPageNation_shouldThrowNullPointerException() {

        articleService.getPagination(null,null);
        verify(articleRepository,times(0)).getTotal();
        verifyNoMoreInteractions(articleRepository);
    }

    @Test(expected = NullPointerException.class)
    public void K_getPageNationBySearch_shouldThrowNullPointerException()  {

        articleService.getPaginationBySearch(null,null,"");
        verify(articleRepository,times(0)).getTotalBySearch("");
        verifyNoMoreInteractions(articleRepository);
    }

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class ObjectFound {

        User user;
        Article mockedArticle;
        List<Article> mockedArticleList =new ArrayList<>();
        @Before
        public void init() {

            mockedArticle =createArticle();

        }

        private Article createArticle(){
            Article article=new Article();
            article.setId(2);
            article.setUserId(1);
            return  article;
        }

        @Test
        public void A_writeArticle_insertAndReturnTrue() {
            when(articleRepository.create(mockedArticle)).thenReturn(1);

            SecurityContext securityContext = returnSecurityContext(new User(),1);
            boolean b = articleService.writeArticle(mockedArticle, SecurityContextHolder.getContext());
            assertThat(b).isTrue();
            verify(articleRepository,times(1)).create(mockedArticle);
            verifyNoMoreInteractions(articleRepository);
        }

        @Test
        public  void B_readArticle_returnArticle() {
            when(articleRepository.read(1)).thenReturn(mockedArticle);

            ArticleDTO articleDTO= articleService.readArticle(1);
            Article actual=articleDTO.getArticle();
            assertThat(actual).isEqualTo(mockedArticle);
            verify(articleRepository,times(1)).read(1);
            verifyNoMoreInteractions(articleRepository);
        }

        @Test
        public  void C_updateArticle_returnTrue(){
            when(articleRepository.update(mockedArticle)).thenReturn(1);

            SecurityContext securityContext= returnSecurityContext(new User(),9);
            boolean actual= articleService.updateArticle(mockedArticle,securityContext);
            assertThat(actual).isEqualTo(true);
            verify(articleRepository,times(1)).update(mockedArticle);
            verifyNoMoreInteractions(articleRepository);
        }

        @Test
        public  void D_deleteArticle_returnTrue()  {
            when(articleRepository.read(1)).thenReturn(mockedArticle);
            when(articleRepository.delete(1)).thenReturn(1);



            User user=new User();
            user.setId(1);
            SecurityContext securityContext= returnSecurityContext(user,9);
            boolean b= articleService.deleteArticle(1,securityContext);
            assertThat(b).isTrue();
            verify(articleRepository,times(1)).read(1);
            verify(articleRepository,times(1)).delete(1);
            verifyNoMoreInteractions(articleRepository);
        }

        @Test
        public  void E_getListArticle_returnListArray() throws InvalidArgumentException {
            when(articleRepository.getList(1,10)).thenReturn(mockedArticleList);

            List<ArticleDTO> articleDTOList= articleService.getList(1,15);
            assertThat(articleDTOList).isEqualTo(mockedArticleList) ;
            verify(articleRepository,times(1)).getList(1,15);
            verifyNoMoreInteractions(articleRepository);
        }

        @Test
        public  void F_getListArticleBySearch_returnListArray() throws InvalidArgumentException {
            when(articleRepository.getListBySearch(1,10,"haha")).thenReturn(mockedArticleList);

            List<ArticleDTO> articleDTOList= articleService.getListBySearch(1,15,"haha");
            assertThat(articleDTOList).isEqualTo(mockedArticleList) ;
            verify(articleRepository,times(1)).getListBySearch(1,15,"haha");
            verifyNoMoreInteractions(articleRepository);

        }

        @Test
        public  void G_getPagination_returnUtilPageNation(){
            when(articleRepository.getTotal()).thenReturn(100);

            UtilPagination up= articleService.getPagination(1,10);
            assertThat(up).isNotNull();
            verify(articleRepository,times(1)).getTotal();
            verifyNoMoreInteractions(articleRepository);
        }

        @Test
        public  void H_getPaginationBySearch_returnUtilPageNation(){
            when(articleRepository.getTotalBySearch("ha")).thenReturn(100);

            UtilPagination up= articleService.getPaginationBySearch(1,10,"ha");
            assertThat(up).isNotNull();

            verify(articleRepository,times(1)).getTotalBySearch("ha");
            verifyNoMoreInteractions(articleRepository);
        }
    }

}

