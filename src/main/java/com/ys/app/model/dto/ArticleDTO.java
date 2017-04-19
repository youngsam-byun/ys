package com.ys.app.model.dto;

import com.ys.app.model.Article;
import com.ys.app.model.User;

/**
 * Created by byun.ys on 4/17/2017.
 */
public class ArticleDTO {

    private Article article;
    private User user;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
