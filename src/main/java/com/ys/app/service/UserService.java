package com.ys.app.service;

import com.ys.app.model.User;
import com.ys.app.util.UtilPagination;
import org.springframework.security.core.context.SecurityContext;

import java.util.List;

/**
 * Created by byun.ys on 4/20/2017.
 */
public interface UserService {
    boolean  createUser(User user, SecurityContext securityContext);
    User read(Integer id);
    User readByEmail(String email) ;
    boolean  updateUser(User user,SecurityContext securityContext);
    boolean updatePassword(User user,SecurityContext securityContext);
    boolean updateTrialCountByOne(String email);
    boolean  deleteUser(Integer id,SecurityContext securityContext) ;
    List<User> getList(Integer pageNo, Integer pageSize) ;
    List<User> getListBySearch(Integer pageNo,Integer pageSize,String keyword) ;
    UtilPagination getPagination(Integer pageNo, Integer pageSize) ;
    UtilPagination getPaginationBySearch(Integer pageNo,Integer pageSize,String keyword) ;
}
