package com.ys.app.service.impl;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.ys.app.model.Article;
import com.ys.app.model.User;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.repo.ArticleRepository;
import com.ys.app.repo.UserRepository;
import com.ys.app.service.ArticleService;
import com.ys.app.util.TestDoubles;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


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
    public void A_writeArticle_shouldThrowNullPointerException() {

        articleService.writeArticle(null, null);

    }

    @Test(expected = AccessDeniedException.class)
    public void B_writeArticle_shouldThrowAccessDeniedException() {

        Article actual = new Article();
        SecurityContext securityContext = returnSecurityContext(0);
        articleService.writeArticle(actual, securityContext);
    }

    @Test(expected = NullPointerException.class)
    public  void C_readArticle_shouldThrowNullopinterException() throws InvalidArgumentException {
        articleService.readArticle(null);
    }


    @Test(expected = NullPointerException.class)
    public void D_updateArticle_shouldThrowNullPointerException() {

        articleService.updateArticle(null, null);

    }

    @Test(expected = AccessDeniedException.class)
    public void E_updateArticle_shouldThrowAccessDeniedException() {

        Article actual = new Article();
        SecurityContext securityContext = returnSecurityContext(0);
        articleService.updateArticle(actual, securityContext);
    }

    @Test(expected = AccessDeniedException.class)
    public void F_updateArticle_shouldThrowAccessDeniedException() {

        Article actual = new Article();
        actual.setUserId(2);
        SecurityContext securityContext = returnSecurityContext(1);
        articleService.updateArticle(actual, securityContext);
    }

    @Test(expected = NullPointerException.class)
    public void G_deleteArticle_shouldThrowNullPointerException() throws InvalidArgumentException {

        articleService.deleteArticle(null,null);
    }

    @Test(expected = NullPointerException.class)
    public void I_getList_shouldThrowNullPointerException() throws InvalidArgumentException {

        articleService.getList(null,null);

    }

    @Test(expected = NullPointerException.class)
    public void J_getListBySearch_shouldThrowNullPointerException() throws InvalidArgumentException {

        articleService.getListBySearch(null,null,"dd");

    }

    @Test(expected = InvalidArgumentException.class)
    public void K_getListBySearch_shouldThrowInvalidArgumentException() throws InvalidArgumentException {

        articleService.getListBySearch(1,15,"");

    }

    @Test(expected = NullPointerException.class)
    public void L_getPageNation_shouldThrowNullPointerException() throws InvalidArgumentException {

        articleService.getPagination(null,null);

    }

    @Test(expected = NullPointerException.class)
    public void M_getPageNationBySearch_shouldThrowNullPointerException() throws InvalidArgumentException {

        articleService.getPaginationBySearch(null,null,"");

    }

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class ObjectFound {

        Article mockedArticle;
        List<Article> mockedArticleList =new ArrayList<>();
        @Before
        public void init() {
            mockedArticle =createArticle();
            when(articleRepository.create(mockedArticle)).thenReturn(1);
            when(articleRepository.read(1)).thenReturn(mockedArticle);
            when(articleRepository.update(mockedArticle)).thenReturn(1);
            when(articleRepository.delete(1)).thenReturn(1);
            when(articleRepository.getList(1,10)).thenReturn(mockedArticleList);
            when(articleRepository.getListBySearch(1,10,"haha")).thenReturn(mockedArticleList);
        }

        private Article createArticle(){
            Article article=new Article();
            article.setId(2);
            article.setUserId(1);
            return  article;
        }

        @Test
        public void A_writeArticle_shouldInsertAndReturnTrue() {
            SecurityContext securityContext = returnSecurityContext(1);
            boolean b = articleService.writeArticle(mockedArticle, SecurityContextHolder.getContext());
            assertThat(b).isTrue();
        }

        @Test
        public  void B_readArticle_shouldReturnArticle() throws InvalidArgumentException {
            ArticleDTO articleDTO= articleService.readArticle(1);
            Article actual=articleDTO.getArticle();
            assertThat(actual).isEqualTo(mockedArticle);
        }

        @Test
        public  void C_updateArticle_shouldReturnTrue(){
            SecurityContext securityContext= returnSecurityContext(9);
            boolean actual= articleService.updateArticle(mockedArticle,securityContext);
            assertThat(actual).isEqualTo(true);
        }

        @Test
        public  void D_deleteArticle_shouldReturnTrue() throws InvalidArgumentException {
            SecurityContext securityContext= returnSecurityContext(1);
            boolean b= articleService.deleteArticle(1,securityContext);
            assertThat(b).isTrue();

        }

        @Test
        public  void E_getListArticle_shouldReturnListArray() throws InvalidArgumentException {
            List<ArticleDTO> articleDTOList= articleService.getList(1,15);
            assertThat(articleDTOList).isEqualTo(mockedArticleList) ;

        }

        @Test
        public  void F_getListArticleBySearch_shouldReturnListArray() throws InvalidArgumentException {
            List<ArticleDTO> articleDTOList= articleService.getListBySearch(1,15,"haha");
            assertThat(articleDTOList).isEqualTo(mockedArticleList) ;

        }
    }


    private static SecurityContext returnSecurityContext(int roleId) {

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("userName", "password");

        User u = new User();
        u.setId(1);
        // Authenticate the user
        Authentication authentication = new Authentication() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {

                u.setRoleid(roleId);
                return u;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }
        };

        authRequest.setDetails(u);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        return securityContext;
    }
}

