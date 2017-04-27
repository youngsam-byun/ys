package com.ys.app.service;

import com.ys.app.model.Article;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.util.UtilPagination;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import java.security.Principal;
import java.util.List;

/**
 * Created by byun.ys on 4/17/2017.
 */
public interface ArticleService {

    void setTable(String table);
    boolean  writeArticle(Article article, Principal principal );
    ArticleDTO readArticle(Integer id) ;
    boolean  updateArticle(Article article,Principal  principal );
    boolean  deleteArticle(Integer id,Principal  principal ) ;
    List<ArticleDTO> getList(Integer pageNo,Integer pageSize) ;
    List<ArticleDTO> getListBySearch(Integer pageNo,Integer pageSize,String keyword) ;
    UtilPagination getPagination(Integer pageNo,Integer pageSize) ;
    UtilPagination getPaginationBySearch(Integer pageNo,Integer pageSize,String keyword) ;

}
