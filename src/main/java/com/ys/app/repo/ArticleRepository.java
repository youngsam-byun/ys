package com.ys.app.repo;

import com.ys.app.model.Article;

import java.util.List;

/**
 * Created by byun.ys on 4/11/2017.
 */

public interface ArticleRepository {

    void setTable(String table);

    int create(Article article);

    Article read(int id);

    int update(Article article);

    int delete(int id);

    List<Article> getList(int pageNo,int pageSize);

    List<Article> getListBySearch(int pageNo,int pageSize, String keyword);

    int getTotal();

    int getTotalBySearch(String keyword);

}
