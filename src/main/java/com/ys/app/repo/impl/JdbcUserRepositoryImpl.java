package com.ys.app.repo.impl;

import com.ys.app.model.User;
import com.ys.app.model.mapper.UserRowMapper;
import com.ys.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.List;

/**
 * Created by byun.ys on 4/17/2017.
 */
@SuppressWarnings("unchecked")
@Repository
public class JdbcUserRepositoryImpl extends BaseRepository<User> implements UserRepository {


    private static final String TABLE_NAME = "User";
    private static final String USER_INS = "User_INS";
    private static final String USER_READ_BY_EMAIL = "User_readByEmail";
    private static final String USER_UPD = "User_UPD";
    private static final String USER_UPD_PWD = "User_UPD_PWD";
    private static final String USER_UPD_TRIAL = "User_UPD_Trial";

    private static final String G_GET_LIST = "G_getList";
    private static final String GET_LIST_BY_SEARCH = "G_getListBySearch";
    private static final String G_GET_TOTAL = "G_getTotal";
    private static final String G_GET_TOTAL_BY_SEARCH = "G_getTotalBySearch";

    private static final String EMAIL = "email";
    private static final String UPDATE_TIME = "updateTime";
    private static final String STR = "str";

    private static final String PAGE_SIZE = "pageSize";
    private static final String PAGE_NO = "pageNo";
    private static final String KEYWORD = "keyword";

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.baseRowMapper = new UserRowMapper();
        this.setTable(TABLE_NAME);
    }

    @Override
    public int create(User user) {
        return super.create(USER_INS, user);
    }

    @Override
    public User read(int id) {
        return super.readbyId(id);
    }

    @Override
    public User readByEmail(String email) {
        return super.readSimple(USER_READ_BY_EMAIL,new SimpleEntry<>(EMAIL,email));
    }

    @Override
    public User readByStr(String str) {
        return  super.readByColumn(STR,str);
    }

    @Override
    public int update(User user) {
        return super.update(USER_UPD, user);
    }

    @Override
    public int updatePassword(User user) {
        return super.update(USER_UPD_PWD,user);
    }

    @Override
    public int updateTrialCountByOne(String email,Date updateTime) {
        return (int)super.executeStoredProcedureForObject(USER_UPD_TRIAL,new SimpleEntry<>(EMAIL,email),new SimpleEntry<>(UPDATE_TIME,updateTime));
    }

    @Override
    public int delete(int id) {
        return super.deleteById(id);
    }

    @Override
    public List<User> getList(int pageNo, int pageSize) {
        return super.getList(G_GET_LIST, new SimpleEntry<>(PAGE_NO, pageNo), new SimpleEntry<>(PAGE_SIZE, pageSize));
    }

    @Override
    public List<User> getListBySearch(int pageNo, int pageSize, String keyword) {
        return super.getList(GET_LIST_BY_SEARCH, new SimpleEntry<>(PAGE_NO, pageNo), new SimpleEntry<>(PAGE_SIZE, pageSize), new SimpleEntry<>(KEYWORD, keyword));

    }

    @Override
    public int getTotal() {
        return super.getTotal(G_GET_TOTAL);
    }

    @Override
    public int getTotalBySearch(String keyword) {
        return super.getTotal(G_GET_TOTAL_BY_SEARCH, new SimpleEntry<>(KEYWORD, keyword));
    }
}
