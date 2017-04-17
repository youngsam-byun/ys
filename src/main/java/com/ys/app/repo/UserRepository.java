package com.ys.app.repo;

import com.ys.app.model.User;

import java.util.List;

/**
 * Created by byun.ys on 4/17/2017.
 */
public interface UserRepository {
    int create(User user);

    User read(int id);

    int update(User user);

    int delete(int id);

    List<User> getList(int pageNo, int pageSize);

    List<User> getListBySearch(int pageNo,int pageSize, String keyword);

    int getTotal();

    int getTotalBySearch(String keyword);
}
