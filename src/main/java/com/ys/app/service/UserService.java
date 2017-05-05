package com.ys.app.service;

import com.ys.app.model.User;
import com.ys.app.util.UtilPagination;

import java.security.Principal;
import java.util.List;

/**
 * Created by byun.ys on 4/20/2017.
 */
public interface UserService {
    boolean create(User user);
    boolean create(User user, Principal principal );
    User read(Integer id);
    User readByEmail(String email);
    User readByStr(String str);
    boolean update(User user, Principal  principal );
    boolean updatePassword(User user,Principal  principal );
    boolean updateTrialCountByOne(String email);
    boolean  deleteUser(Integer id,Principal  principal ) ;
    List<User> getList(Integer pageNo, Integer pageSize) ;
    List<User> getListBySearch(Integer pageNo,Integer pageSize,String keyword) ;
    UtilPagination getPagination(Integer pageNo, Integer pageSize) ;
    UtilPagination getPaginationBySearch(Integer pageNo,Integer pageSize,String keyword) ;
}
