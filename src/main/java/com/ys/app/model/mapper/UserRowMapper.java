package com.ys.app.model.mapper;

import com.ys.app.model.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by byun.ys on 4/11/2017.
 */
public class UserRowMapper extends BaseRowMapper<User> {


    private static final String UPDATE_TIME = "updateTime";
    private static final String EXPIRE_TIME = "expireTime";
    private static final String REFRESH_TOKEN = "refreshToken";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE_ID = "roleId";
    private static final String STR = "str";
    private static final String ENABLED = "enabled";
    private static final String NOT_LOCKED = "notLocked";
    private static final String CREATE_TIME = "createTime";
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String ID = "id";
    private static final String PROVIDER_ID = "providerId";
    private static final String CONNECTION_ID = "connectionId";
    private static final String PROVIDER_CONNECTION_ID = "providerConnectionId";
    private static final String RANK = "rank";
    private static final String DISPLAY_NAME = "displayName";
    private static final String PROFILE_URL = "profileUrl";
    private static final String IMAGE_URL = "imageUrl";
    private static final String SECRET = "secret";
    private static final String TRIAL = "trial";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setProviderId(PROVIDER_ID);
        user.setConnectionId(CONNECTION_ID);
        user.setProviderConnectionid(PROVIDER_CONNECTION_ID);
        user.setRank(rs.getInt(RANK));
        user.setDisplayName(rs.getString(DISPLAY_NAME));
        user.setProfileUrl(rs.getString(PROFILE_URL));
        user.setImageUrl(rs.getString(IMAGE_URL));
        user.setAccessToken(rs.getString(ACCESS_TOKEN));
        user.setSecret(rs.getString(SECRET));
        user.setRefreshToken(rs.getString(REFRESH_TOKEN));
        user.setExpireTime(rs.getLong(EXPIRE_TIME));
        user.setId(rs.getInt(ID));
        user.setEmail(rs.getString(EMAIL));
        user.setUsername(rs.getString(USERNAME));
        user.setPassword(rs.getString(PASSWORD));
        user.setRoleId(rs.getInt(ROLE_ID));
        user.setStr(rs.getString(STR));
        user.setEnabled(rs.getBoolean(ENABLED));
        user.setNotLocked(rs.getBoolean(NOT_LOCKED));
        user.setCreateTime(rs.getTimestamp(CREATE_TIME));
        user.setUpdateTime(rs.getTimestamp(UPDATE_TIME));
        user.setTrial(rs.getInt(TRIAL));


        return user;
    }

    @Override
    public MapSqlParameterSource createParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue(PROVIDER_ID,user.getProviderId())
                .addValue(CONNECTION_ID,user.getConnectionId())
                .addValue(PROVIDER_CONNECTION_ID,user.getProviderConnectionid())
                .addValue(RANK,user.getRank())
                .addValue(DISPLAY_NAME,user.getDisplayName())
                .addValue(PROFILE_URL,user.getProfileUrl())
                .addValue(IMAGE_URL,user.getImageUrl())
                .addValue(ACCESS_TOKEN,user.getAccessToken())
                .addValue(SECRET,user.getSecret())
                .addValue(REFRESH_TOKEN,user.getRefreshToken())
                .addValue(EXPIRE_TIME,user.getExpireTime())
//                /.addValue(ID,user.getId())
                .addValue(EMAIL,user.getEmail())
                .addValue(USERNAME,user.getUsername())
                .addValue(PASSWORD,user.getPassword())
                .addValue(ROLE_ID,user.getRoleId())
                .addValue(STR,user.getStr())
                .addValue(ENABLED,user.isEnabled())
                .addValue(NOT_LOCKED,user.isNotLocked())
                .addValue(CREATE_TIME,user.getCreateTime())
                .addValue(UPDATE_TIME,user.getUpdateTime())
                .addValue(TRIAL,user.getTrial())
                ;
    }

    @Override
    public MapSqlParameterSource updateParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue(PROVIDER_ID,user.getProviderId())
                .addValue(CONNECTION_ID,user.getConnectionId())
                .addValue(PROVIDER_CONNECTION_ID,user.getProviderConnectionid())
                .addValue(RANK,user.getRank())
                .addValue(DISPLAY_NAME,user.getDisplayName())
                .addValue(PROFILE_URL,user.getProfileUrl())
                .addValue(IMAGE_URL,user.getImageUrl())
                .addValue(ACCESS_TOKEN,user.getAccessToken())
                .addValue(SECRET,user.getSecret())
                .addValue(REFRESH_TOKEN,user.getRefreshToken())
                .addValue(EXPIRE_TIME,user.getExpireTime())
                .addValue(ID,user.getId())
                .addValue(EMAIL,user.getEmail())
                .addValue(USERNAME,user.getUsername())
                .addValue(PASSWORD,user.getPassword())
                .addValue(ROLE_ID,user.getRoleId())
                .addValue(STR,user.getStr())
                .addValue(ENABLED,user.isEnabled())
                .addValue(NOT_LOCKED,user.isNotLocked())
                .addValue(CREATE_TIME,user.getCreateTime())
                .addValue(UPDATE_TIME,user.getUpdateTime())
                .addValue(TRIAL,user.getTrial())
                ;
    }
}
