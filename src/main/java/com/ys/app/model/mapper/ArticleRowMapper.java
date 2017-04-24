package com.ys.app.model.mapper;

import com.ys.app.model.Article;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by byun.ys on 4/11/2017.
 */
public class ArticleRowMapper extends BaseRowMapper<Article> {

    private static final String CATEGORY_ID = "categoryId";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String BODY = "body";
    private static final String USER_ID = "userId";
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";
    private static final String NO_OF_READ = "noOfRead";
    private static final String DELETED = "deleted";
    private static final String IP="ip";
    private static final String UPVOTE="upvote";

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {

        Article article = new Article();

        article.setId(rs.getInt(ID));
        article.setCategoryId(rs.getShort(CATEGORY_ID));
        article.setTitle(rs.getString(TITLE));
        article.setBody(rs.getString(BODY));
        article.setCreateTime(rs.getTimestamp(CREATE_TIME));
        article.setUpdateTime(rs.getTimestamp(UPDATE_TIME));
        article.setUserId(rs.getInt(USER_ID));
        article.setDeleted(rs.getBoolean(DELETED));
        article.setIp(rs.getString(IP));
        article.setUpvote(rs.getInt(UPVOTE));

        return article;

    }

    @Override
    public MapSqlParameterSource createParameterSource(Article article) {
        return new MapSqlParameterSource()
                //.addValue("id",article.getId())
                .addValue(CATEGORY_ID, article.getCategoryId())
                .addValue(TITLE, article.getTitle())
                .addValue(BODY, article.getBody())
                .addValue(CREATE_TIME, article.getCreateTime())
                .addValue(UPDATE_TIME, article.getUpdateTime())
                .addValue(USER_ID, article.getUserId())
                .addValue(NO_OF_READ, article.getNoOfRead())
                .addValue(DELETED, article.isDeleted())
                .addValue(IP,article.getIp())
                .addValue(UPVOTE,article.getUpvote());

    }

    @Override
    public MapSqlParameterSource updateParameterSource(Article article) {
        return new MapSqlParameterSource()
                .addValue(ID, article.getId())
                .addValue(CATEGORY_ID, article.getCategoryId())
                .addValue(TITLE, article.getTitle())
                .addValue(BODY, article.getBody())
                .addValue(CREATE_TIME, article.getCreateTime())
                .addValue(UPDATE_TIME, article.getUpdateTime())
                .addValue(USER_ID, article.getUserId())
                .addValue(NO_OF_READ, article.getNoOfRead())
                .addValue(DELETED, article.isDeleted())
                .addValue(IP,article.getIp())
                .addValue(UPVOTE,article.getUpvote());
    }
}
