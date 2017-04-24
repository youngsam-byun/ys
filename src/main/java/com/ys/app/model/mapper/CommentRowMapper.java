package com.ys.app.model.mapper;

import com.ys.app.model.Comment;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by byun.ys on 4/11/2017.
 */
public class CommentRowMapper extends BaseRowMapper<Comment> {

    private static final String ID = "id";
    private static final String ARTICLE_ID = "articleId";
    private static final String CATEGORY_ID = "categoryId";
    private static final String BODY = "body";
    private static final String USER_ID = "userId";
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";
    private static final String DELETED = "deleted";
    private static final String IP = "ip";
    private static final String UPVOTE = "upvote";

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {

        Comment comment = new Comment();

        comment.setId(rs.getInt(ID));
        comment.setArticleId(rs.getInt(ARTICLE_ID));
        comment.setCategoryId(rs.getShort(CATEGORY_ID));
        comment.setBody(rs.getString(BODY));
        comment.setCreateTime(rs.getTimestamp(CREATE_TIME));
        comment.setUpdateTime(rs.getTimestamp(UPDATE_TIME));
        comment.setUserId(rs.getInt(USER_ID));
        comment.setDeleted(rs.getBoolean(DELETED));
        comment.setIp(rs.getString(IP));
        comment.setUpvote(rs.getInt(UPVOTE));

        return comment;

    }

    @Override
    public MapSqlParameterSource createParameterSource(Comment comment) {
        return new MapSqlParameterSource()
                //.addValue("id",comment.getId())
                .addValue(ARTICLE_ID, comment.getArticleId())
                .addValue(CATEGORY_ID, comment.getCategoryId())
                .addValue(BODY, comment.getBody())
                .addValue(CREATE_TIME, comment.getCreateTime())
                .addValue(UPDATE_TIME, comment.getUpdateTime())
                .addValue(USER_ID, comment.getUserId())
                .addValue(DELETED, comment.isDeleted())
                .addValue(IP, comment.getIp())
                .addValue(UPVOTE, comment.getUpvote());

    }

    @Override
    public MapSqlParameterSource updateParameterSource(Comment comment) {
        return new MapSqlParameterSource()
                .addValue("id",comment.getId())
                .addValue(ARTICLE_ID, comment.getArticleId())
                .addValue(CATEGORY_ID, comment.getCategoryId())
                .addValue(BODY, comment.getBody())
                .addValue(CREATE_TIME, comment.getCreateTime())
                .addValue(UPDATE_TIME, comment.getUpdateTime())
                .addValue(USER_ID, comment.getUserId())
                .addValue(DELETED, comment.isDeleted())
                .addValue(IP, comment.getIp())
                .addValue(UPVOTE, comment.getUpvote());
    }
}
