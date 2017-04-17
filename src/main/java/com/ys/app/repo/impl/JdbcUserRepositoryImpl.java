package com.ys.app.repo.impl;

import com.ys.app.model.User;
import com.ys.app.model.mapper.UserRowMapper;
import com.ys.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

/**
 * Created by byun.ys on 4/17/2017.
 */
@Repository
public class JdbcUserRepositoryImpl extends BaseRepository<User> implements UserRepository {


    private static final String TABLE = "User";
    private static final String USER_INS = "User_INS";
    private static final String USER_UPD = "User_UPD";
    private static final String GET_LIST = "getList";
    private static final String PAGE_SIZE = "pageSize";
    private static final String PAGE_NO = "pageNo";
    private static final String KEYWORD = "keyword";
    private static final String GET_TOTAL = "getTotal";
    private static final String GET_TOTAL_BY_SEARCH = "getTotalBySearch";

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.baseRowMapper = new UserRowMapper();
        this.setTable(TABLE);
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
    public int update(User user) {
        return super.update(USER_UPD, user);
    }

    @Override
    public int delete(int id) {
        return super.deleteById(id);
    }

    @Override
    public List<User> getList(int pageNo, int pageSize) {
        return super.getList(GET_LIST, new SimpleEntry<String, Object>(PAGE_NO, pageNo), new SimpleEntry<String, Object>(PAGE_SIZE, pageSize));
    }

    @Override
    public List<User> getListBySearch(int pageNo, int pageSize, String keyword) {
        return super.getList("getListBySearch", new SimpleEntry<String, Object>(PAGE_NO, pageNo), new SimpleEntry<String, Object>(PAGE_SIZE, pageSize), new SimpleEntry<String, Object>(KEYWORD, keyword));

    }

    @Override
    public int getTotal() {
        return super.getTotal(GET_TOTAL);
    }

    @Override
    public int getTotalBySearch(String keyword) {
        return super.getTotal(GET_TOTAL_BY_SEARCH, new SimpleEntry<>(KEYWORD, keyword));
    }
}
