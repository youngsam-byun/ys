package com.ys.app.service.impl;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.ys.app.model.Article;
import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.repo.ArticleRepository;
import com.ys.app.repo.UserRepository;
import com.ys.app.service.ArticleService;
import com.ys.app.util.UtilPagination;
import com.ys.app.util.UtilValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byun.ys on 4/17/2017.
 */

@SuppressWarnings({"ALL", "PointlessBooleanExpression"})
@Service
public class ArticleServiceImpl implements ArticleService {

    @Value("${articleservice.write.nopermission}")
    private String NO_PERMISSION_TO_WRITE_ARTICLE;// = "No Permission to write article";
    @Value("${articleservice.update.nopermission}")
    private String NO_PERMISSION_TO_UPDATE_ARTICLE;// = "No permission to update article";
    @Value("${articleservice.delete.nopermission}")
    private String NO_PERMISSION_TO_DELETE_ARTICLE;// = "No permission to delete article";
    @Value("${articleservice.keyword.notempty}")
    private String SEARCH_KEYWORD_SHOULD_NOT_BE_EMPTY;// = "Search keyword should not be empty";

    private ArticleRepository articleRepository;
    private UserRepository userRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {

        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public boolean writeArticle(Article article, SecurityContext securityContext) {
        if (UtilValidation.IsNull(article, securityContext))
            throw new NullPointerException();


        if (hasWritePermission(securityContext, Role.USER) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_WRITE_ARTICLE);

        return articleRepository.create(article) >= 1;
    }

    @Override
    public ArticleDTO readArticle(Integer id) {
        if (UtilValidation.IsNull(id))
            throw new NullPointerException();

        Article article = articleRepository.read(id);
        if (article == null)
            return null;

        int userId = article.getUserId();
        User user = userRepository.read(userId);

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setArticle(article);
        articleDTO.setUser(user);

        return articleDTO;

    }

    @Override
    public boolean updateArticle(Article article, SecurityContext securityContext) {
        if (UtilValidation.IsNull(article, securityContext))
            throw new NullPointerException();

        if (hasWritePermission(securityContext, Role.USER) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_UPDATE_ARTICLE);

        if (hasUpdatePermission(securityContext, article) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_UPDATE_ARTICLE);

        return articleRepository.update(article) == 1;
    }

    @Override
    public boolean deleteArticle(Integer id, SecurityContext securityContext) {
        if (UtilValidation.IsNull(id))
            throw new NullPointerException();

        if (hasDeletePermission(securityContext, id) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_DELETE_ARTICLE);

        return articleRepository.delete(id) == 1;
    }


    @Override
    public List<ArticleDTO> getList(Integer pageNo, Integer pageSize) {
        if (UtilValidation.IsNull(pageNo, pageSize))
            throw new NullPointerException();

        List<Article> articleList = articleRepository.getList(pageNo, pageSize);

        return getArticleDTOList(articleList);

    }

    @Override
    public List<ArticleDTO> getListBySearch(Integer pageNo, Integer pageSize, String keyword) throws InvalidArgumentException {
        if (UtilValidation.IsNull(pageNo, pageSize, keyword))
            throw new NullPointerException();

        if (keyword.isEmpty())
            throw new InvalidArgumentException(new String[]{SEARCH_KEYWORD_SHOULD_NOT_BE_EMPTY});


        List<Article> articleList = articleRepository.getListBySearch(pageNo, pageSize, keyword);


        return getArticleDTOList(articleList);

    }

    @Override
    public UtilPagination getPagination(Integer pageNo, Integer pageSize) {
        if (UtilValidation.IsNull(pageNo))
            throw new NullPointerException();

        int total = articleRepository.getTotal();
        return new UtilPagination(pageNo, total, pageSize);
    }

    @Override
    public UtilPagination getPaginationBySearch(Integer pageNo, Integer pageSize, String keyword) throws InvalidArgumentException {
        if (UtilValidation.IsNull(pageNo, keyword))
            throw new NullPointerException();

        if (keyword.isEmpty())
            throw new InvalidArgumentException(new String[]{SEARCH_KEYWORD_SHOULD_NOT_BE_EMPTY});

        int total = articleRepository.getTotalBySearch(keyword);
        return new UtilPagination(pageNo, total, pageSize);
    }

    private boolean hasWritePermission(SecurityContext securityContext, Role role) {
        User user = (User) securityContext.getAuthentication().getDetails();
        int roleId = user.getRoleid();
        return roleId >= role.getId();
    }

    private boolean hasUpdatePermission(SecurityContext securityContext, Article article) {
        User user = (User) securityContext.getAuthentication().getDetails();
        int roleId = user.getRoleid();
        int id = user.getId();
        int userId = article.getUserId();

        return id == userId || roleId >= Role.SUPERADMIN.getId();

    }

    private boolean hasDeletePermission(SecurityContext securityContext, Integer articleId) {
        User user = (User) securityContext.getAuthentication().getDetails();
        int roleId = user.getRoleid();
        int id = user.getId();

        Article article = articleRepository.read(articleId);
        int userId = article.getUserId();

        return id == userId || roleId >= Role.SUPERADMIN.getId();
    }

    private List<ArticleDTO> getArticleDTOList(List<Article> articleList) {
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        for (Article article : articleList) {
            int userId = article.getUserId();
            User user = userRepository.read(userId);
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setArticle(article);
            articleDTO.setUser(user);

            articleDTOList.add(articleDTO);
        }
        return articleDTOList;
    }
}
