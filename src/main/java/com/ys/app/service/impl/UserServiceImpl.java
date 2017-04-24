package com.ys.app.service.impl;

import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.repo.UserRepository;
import com.ys.app.service.UserService;
import com.ys.app.util.UtilPagination;
import com.ys.app.util.UtilValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by byun.ys on 4/20/2017.
 */
@Service
public class UserServiceImpl implements UserService {


    @Value("${userService.updateUser.noPermission}")
    private static final String NO_PERMISSION_TO_UPDATE_USER = "userService.updateUser.noPermission";

    @Value("${userService.creatrUser.noPermission}")
    private final String NO_PERMISSION_TO_CREATE_USER="userService.createUser.noPermission";

    private UserRepository userRepository;
    private  Role role;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        role=Role.OPERATION;
    }

    @Override
    public boolean createUser(User user, SecurityContext securityContext) {
        if(UtilValidation.isNull(user,securityContext))
            throw new NullPointerException();

        if(hasCreatePermission(securityContext,role)==false)
            throw new AccessDeniedException(NO_PERMISSION_TO_CREATE_USER);

        return userRepository.create(user)>=1;

    }

    @Override
    public User read(Integer id) {

        if(UtilValidation.isNull(id))
            throw new NullPointerException();

        return userRepository.read(id);
    }

    @Override
    public User readByEmail(String email) {

        if(UtilValidation.isNull(email))
            throw new NullPointerException();

        return userRepository.readByEmail(email);
    }

    @Override
    public boolean updateUser(User user, SecurityContext securityContext) {
        if(UtilValidation.isNull(user,securityContext))
            throw new NullPointerException();

        if(hasUpdatePermission(securityContext,role,user)==false)
            throw new AccessDeniedException(NO_PERMISSION_TO_UPDATE_USER);

        return userRepository.update(user)==1;
    }

    @Override
    public boolean updatePassword(User user, SecurityContext securityContext) {

        if(UtilValidation.isNull(user,securityContext))
            throw new NullPointerException();

        if(hasUpdatePermission(securityContext,role,user)==false)
            throw new AccessDeniedException(NO_PERMISSION_TO_UPDATE_USER);


        return userRepository.updatePassword(user)==1;
    }

    @Override
    public boolean updateTrialCountByOne(String email) {
        return userRepository.updateTrialCountByOne(email,new Date())==1;
    }

    @Override
    public boolean deleteUser(Integer id, SecurityContext securityContext) {

        if(UtilValidation.isNull(id,securityContext))
            throw new NullPointerException();

        if(hasDeletePermission(securityContext,role,id)==false)
            throw new AccessDeniedException(NO_PERMISSION_TO_UPDATE_USER);

        return userRepository.delete(id)==1;


    }

    @Override
    public List<User> getList(Integer pageNo, Integer pageSize) {

        if(UtilValidation.isNull(pageNo,pageSize))
            throw new NullPointerException();

        return userRepository.getList(pageNo,pageSize);
    }

    @Override
    public List<User> getListBySearch(Integer pageNo, Integer pageSize, String keyword) {

        if(UtilValidation.isNull(pageNo,pageSize,keyword))
            throw new NullPointerException();

        return  userRepository.getListBySearch(pageNo,pageSize,keyword);
    }

    @Override
    public UtilPagination getPagination(Integer pageNo, Integer pageSize) {

        if(UtilValidation.isNull(pageNo,pageSize))
            throw new NullPointerException();

        int total=userRepository.getTotal();
        return new UtilPagination(pageNo,total,pageSize);
    }

    @Override
    public UtilPagination getPaginationBySearch(Integer pageNo, Integer pageSize, String keyword) {

        if(UtilValidation.isNull(pageNo,pageSize,keyword))
            throw new NullPointerException();

        int total=userRepository.getTotalBySearch(keyword);
        return new UtilPagination(pageNo,total,pageSize);
    }

    private boolean hasCreatePermission(SecurityContext securityContext, Role role) {
        User user = getUser(securityContext);
        int roleId = user.getRoleid();
        return roleId >= role.getId();
    }

    private boolean hasUpdatePermission(SecurityContext securityContext, Role role,User u) {
        User user = getUser(securityContext);
        int id=user.getId();
        int roleId = user.getRoleid();

        int userId=u.getId();

        return id==userId || roleId >= role.getId();
    }
    private boolean hasDeletePermission(SecurityContext securityContext, Role role, Integer userId) {
        User user=getUser(securityContext);
        int id=user.getId();
        int roleId=user.getRoleid();

        return id==userId || roleId>=role.getId();
    }
    private User getUser(SecurityContext securityContext) {
        return (User) securityContext.getAuthentication().getDetails();
    }


}
