package com.ys.app.model.mapper;

import com.ys.app.model.Category;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by byun.ys on 4/11/2017.
 */
public class CategoryRowMapper extends BaseRowMapper<Category> {


    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DELETED = "deleted";
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {

        Category category = new Category();

        category.setId(rs.getInt(ID));
        category.setName(rs.getString(NAME));
        category.setCreateTime(rs.getTimestamp(CREATE_TIME));
        category.setUpdateTime(rs.getTimestamp(UPDATE_TIME));
        category.setDeleted(rs.getBoolean(DELETED));

        return category;

    }

    @Override
    public MapSqlParameterSource createParameterSource(Category category) {
        return new MapSqlParameterSource()
                //.addValue("id",Category.getId())
                .addValue(NAME, category.getName())
                .addValue(CREATE_TIME,category.getCreateTime())
                .addValue(UPDATE_TIME,category.getUpdateTime())
                .addValue(DELETED, category.isDeleted());

    }

    @Override
    public MapSqlParameterSource updateParameterSource(Category category) {
        return new MapSqlParameterSource()
                .addValue("id", category.getId())
                .addValue(NAME, category.getName())
                .addValue(CREATE_TIME,category.getCreateTime())
                .addValue(UPDATE_TIME,category.getUpdateTime())

                .addValue(DELETED, category.isDeleted());
    }
}
