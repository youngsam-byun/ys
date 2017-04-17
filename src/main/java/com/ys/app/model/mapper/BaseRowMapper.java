package com.ys.app.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by byun.ys on 4/11/2017.
 */
public abstract class BaseRowMapper<T> implements RowMapper<T>{

    public abstract T mapRow(ResultSet rs, int rowNum) throws SQLException;

    public abstract MapSqlParameterSource createParameterSource(T t);

    public abstract MapSqlParameterSource updateParameterSource(T t);

}


