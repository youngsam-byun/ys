package com.ys.app.service;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.ys.app.model.Article;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.util.UtilPagination;
import org.springframework.security.core.context.SecurityContext;

import java.util.List;

/**
 * Created by byun.ys on 4/17/2017.
 */
public interface ArticleService {

    boolean  writeArticle(Article article, SecurityContext securityContext);
    ArticleDTO readArticle(Integer id) throws InvalidArgumentException ;
    boolean  updateArticle(Article article,SecurityContext securityContext);
    boolean  deleteArticle(Integer id,SecurityContext securityContext) throws InvalidArgumentException;
    List<ArticleDTO> getList(Integer pageNo,Integer pageSize) throws InvalidArgumentException;
    List<ArticleDTO> getListBySearch(Integer pageNo,Integer pageSize,String keyword) throws InvalidArgumentException;
    UtilPagination getPagination(Integer pageNo,Integer pageSize) throws InvalidArgumentException;
    UtilPagination getPaginationBySearch(Integer pageNo,Integer pageSize,String keyword) throws InvalidArgumentException;

}
