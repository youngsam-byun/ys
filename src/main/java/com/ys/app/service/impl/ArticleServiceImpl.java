package com.ys.app.service.impl;

import com.ys.app.model.Article;
import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.repo.ArticleRepository;
import com.ys.app.repo.UserRepository;
import com.ys.app.security.CustomUserDetails;
import com.ys.app.service.ArticleService;
import com.ys.app.util.UtilPagination;
import com.ys.app.util.UtilValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by byun.ys on 4/17/2017.
 */

@SuppressWarnings({"ALL", "PointlessBooleanExpression"})
@Service
public class ArticleServiceImpl implements ArticleService {


    @Value("${articleService.write.noPermission?:}")
    private String NO_PERMISSION_TO_WRITE_ARTICLE;// = "articleservice.write.nopermission";
    @Value("${articleService.update.noPermission?:}")
    private String NO_PERMISSION_TO_UPDATE_ARTICLE;// = "articleservice.update.nopermission";
    @Value("${articleService.delete.noPermission?:}")
    private String NO_PERMISSION_TO_DELETE_ARTICLE;// = "articleservice.delete.nopermission";


    private ArticleRepository articleRepository;
    private UserRepository userRepository;
    private Role role;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {

        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        role = Role.USER;
    }


    @Override
    public void setTable(String table) {
        articleRepository.setTable(table);
    }

    @Override
    public boolean create(Article article, Principal principal) {
        if (UtilValidation.isNull(article, principal))
            throw new NullPointerException();


        if (hasWritePermission(principal, role) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_WRITE_ARTICLE);

        return articleRepository.create(article) >= 1;
    }

    @Override
    public ArticleDTO read(Integer id) {
        if (UtilValidation.isNull(id))
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
    public boolean update(Article article, Principal principal) {
        if (UtilValidation.isNull(article, principal))
            throw new NullPointerException();

        if (hasUpdatePermission(principal, article) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_UPDATE_ARTICLE);

        return articleRepository.update(article) == 1;
    }

    @Override
    public boolean delete(Integer id, Principal principal) {
        if (UtilValidation.isNull(id))
            throw new NullPointerException();

        if (hasDeletePermission(principal, id) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_DELETE_ARTICLE);

        return articleRepository.delete(id) == 1;
    }


    @Override
    public List<ArticleDTO> getList(Integer pageNo, Integer pageSize) {
        if (UtilValidation.isNull(pageNo, pageSize))
            throw new NullPointerException();


        List<Article> articleList = articleRepository.getList(pageNo, pageSize);

        return getArticleDTOList(articleList);

    }

    @Override
    public List<ArticleDTO> getListBySearch(Integer pageNo, Integer pageSize, String keyword) {
        if (UtilValidation.isNull(pageNo, pageSize, keyword))
            throw new NullPointerException();

        List<Article> articleList = articleRepository.getListBySearch(pageNo, pageSize, keyword);


        return getArticleDTOList(articleList);

    }

    @Override
    public UtilPagination getPagination(Integer pageNo, Integer pageSize) {
        if (UtilValidation.isNull(pageNo))
            throw new NullPointerException();


        int total = articleRepository.getTotal();
        return new UtilPagination(pageNo, total, pageSize);
    }

    @Override
    public UtilPagination getPaginationBySearch(Integer pageNo, Integer pageSize, String keyword) {
        if (UtilValidation.isNull(pageNo, keyword))
            throw new NullPointerException();


        int total = articleRepository.getTotalBySearch(keyword);
        return new UtilPagination(pageNo, total, pageSize);
    }

    private boolean hasWritePermission(Principal principal, Role role) {
        User user = getUser(principal);
        int roleId = user.getRoleId();
        return roleId >= role.getId();
    }

    private User getUser(Principal principal) {
        return ((CustomUserDetails) principal).getUser();
    }

    public boolean hasUpdatePermission(Principal principal, Article article) {
        User user = getUser(principal);
        int roleId = user.getRoleId();
        int id = user.getId();
        int userId = article.getUserId();

        return id == userId || roleId >= role.getId();

    }

    private boolean hasDeletePermission(Principal principal, Integer articleId) {
        User user = getUser(principal);
        int roleId = user.getRoleId();
        int id = user.getId();

        Article article = articleRepository.read(articleId);
        int userId = article.getUserId();

        return id == userId || roleId >= role.getId();
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
