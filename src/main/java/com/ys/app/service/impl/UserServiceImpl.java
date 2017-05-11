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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import static com.ys.app.security.service.CustomUserDetailsService.extractUser;

/**
 * Created by byun.ys on 4/20/2017.
 */
@Service
public class UserServiceImpl implements UserService {


    @Value("${userService.update.noPermission?:userService.update.noPermission}")
    private  String NO_PERMISSION_TO_UPDATE_USER;// = "userService.updateUser.noPermission";

    @Value("${userService.create.noPermission?:userService.update.noPermission}")
    private  String NO_PERMISSION_TO_CREATE_USER;// = "userService.createUser.noPermission";

    private UserRepository userRepository;
    private Role role;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        role = Role.OPERATION;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public boolean create(User user) {
        if (UtilValidation.isNull(user))
            throw new NullPointerException();


        String encoded = encodePassword(user);
        user.setPassword(encoded);

        return userRepository.create(user) >= 1;
    }

    @Override
    public boolean create(User user, Principal principal) {
        if (UtilValidation.isNull(user, principal))
            throw new NullPointerException();

        if (hasCreatePermission(principal, role) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_CREATE_USER);


        user.setNotLocked(true);
        user.setEnabled(true);
        String encoded = encodePassword(user);
        user.setPassword(encoded);

        return userRepository.create(user) >= 1;

    }

    private String encodePassword(User user) {
        String password = user.getPassword();
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public User read(Integer id) {

        if (UtilValidation.isNull(id))
            throw new NullPointerException();

        return userRepository.read(id);
    }

    @Override
    public User readByEmail(String email) {

        if (UtilValidation.isNull(email))
            throw new NullPointerException();

        return userRepository.readByEmail(email);
    }

    @Override
    public User readByStr(String str) {
        return  userRepository.readByStr(str);
    }

    @Override
    public boolean update(User user, Principal principal) {
        if (UtilValidation.isNull(user, principal))
            throw new NullPointerException();

        if (hasUpdatePermission(principal, role, user) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_UPDATE_USER);

        String encoded = encodePassword(user);
        user.setPassword(encoded);

        return userRepository.update(user) == 1;
    }

    @Override
    public boolean updatePassword(User user, Principal principal) {

        if (UtilValidation.isNull(user, principal))
            throw new NullPointerException();

        if (hasUpdatePermission(principal, role, user) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_UPDATE_USER);

        String encoded = encodePassword(user);
        user.setPassword(encoded);

        return userRepository.updatePassword(user) == 1;
    }

    @Override
    public boolean updateTrialCountByOne(String email) {
        return userRepository.updateTrialCountByOne(email, new Date()) == 1;
    }

    @Override
    public boolean deleteUser(Integer id, Principal principal) {

        if (UtilValidation.isNull(id, principal))
            throw new NullPointerException();

        if (hasDeletePermission(principal, role, id) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_UPDATE_USER);

        return userRepository.delete(id) == 1;


    }

    @Override
    public List<User> getList(Integer pageNo, Integer pageSize) {

        if (UtilValidation.isNull(pageNo, pageSize))
            throw new NullPointerException();

        return userRepository.getList(pageNo, pageSize);
    }

    @Override
    public List<User> getListBySearch(Integer pageNo, Integer pageSize, String keyword) {

        if (UtilValidation.isNull(pageNo, pageSize, keyword))
            throw new NullPointerException();

        return userRepository.getListBySearch(pageNo, pageSize, keyword);
    }

    @Override
    public UtilPagination getPagination(Integer pageNo, Integer pageSize) {

        if (UtilValidation.isNull(pageNo, pageSize))
            throw new NullPointerException();

        int total = userRepository.getTotal();
        return new UtilPagination(pageNo, total, pageSize);
    }

    @Override
    public UtilPagination getPaginationBySearch(Integer pageNo, Integer pageSize, String keyword) {

        if (UtilValidation.isNull(pageNo, pageSize, keyword))
            throw new NullPointerException();

        int total = userRepository.getTotalBySearch(keyword);
        return new UtilPagination(pageNo, total, pageSize);
    }

    private boolean hasCreatePermission(Principal principal, Role role) {
        User user = extractUser(principal);
        int roleId = user.getRoleId();
        return roleId >= role.getId();
    }

    private boolean hasUpdatePermission(Principal principal, Role role, User u) {
        User user = extractUser(principal);
        int id = user.getId();
        int roleId = user.getRoleId();

        int userId = u.getId();

        return id == userId || roleId >= role.getId();
    }

    private boolean hasDeletePermission(Principal principal, Role role, Integer userId) {
        User user = extractUser(principal);
        int id = user.getId();
        int roleId = user.getRoleId();

        return id == userId || roleId >= role.getId();
    }

    
}
