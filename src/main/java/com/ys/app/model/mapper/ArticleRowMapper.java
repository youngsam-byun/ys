package com.ys.app.model.mapper;

import com.ys.app.model.Article;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by byun.ys on 4/11/2017.
 */
public class ArticleRowMapper extends BaseRowMapper<Article> {

    public static final String CATEGORY_ID = "categoryId";
    private static final String ID = "id";
    private static final String NO = "no";
    private static final String LEVEL = "level";
    private static final String SEQUENCE = "sequence";
    private static final String TITLE = "title";
    private static final String BODY = "body";
    private static final String USER_ID = "userId";
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";
    private static final String NO_OF_READ = "noOfRead";
    private static final String DELETED = "deleted";

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {

        Article article = new Article();

        article.setId(rs.getInt(ID));
        article.setCategoryId(rs.getShort(CATEGORY_ID));
        article.setNo(rs.getInt(NO));
        article.setLevel(rs.getShort(LEVEL));
        article.setSequence(rs.getShort(SEQUENCE));
        article.setTitle(rs.getString(TITLE));
        article.setBody(rs.getString(BODY));
        article.setCreateTime(rs.getTimestamp(CREATE_TIME));
        article.setUpdateTime(rs.getTimestamp(UPDATE_TIME));
        article.setUserid(rs.getInt(USER_ID));
        article.setDeleted(rs.getBoolean(DELETED));

        return article;

    }

    @Override
    public MapSqlParameterSource createParameterSource(Article article) {
        return new MapSqlParameterSource()
                //.addValue("id",article.getId())
                .addValue(CATEGORY_ID, article.getCategoryId())
                .addValue(NO, article.getNo())
                .addValue(LEVEL, article.getLevel())
                .addValue(SEQUENCE, article.getSequence())
                .addValue(TITLE, article.getTitle())
                .addValue(BODY, article.getBody())
                .addValue(CREATE_TIME, article.getCreateTime())
                .addValue(UPDATE_TIME, article.getUpdateTime())
                .addValue(USER_ID, article.getUserid())
                .addValue(NO_OF_READ, article.getNoofread())
                .addValue(DELETED, article.isDeleted());

    }

    @Override
    public MapSqlParameterSource updateParameterSource(Article article) {
        return new MapSqlParameterSource()
                .addValue(ID, article.getId())
                .addValue(CATEGORY_ID, article.getCategoryId())
                .addValue(NO, article.getNo())
                .addValue(LEVEL, article.getLevel())
                .addValue(SEQUENCE, article.getSequence())
                .addValue(TITLE, article.getTitle())
                .addValue(BODY, article.getBody())
                .addValue(CREATE_TIME, article.getCreateTime())
                .addValue(UPDATE_TIME, article.getUpdateTime())
                .addValue(USER_ID, article.getUserid())
                .addValue(NO_OF_READ, article.getNoofread())
                .addValue(DELETED, article.isDeleted());
    }
}
