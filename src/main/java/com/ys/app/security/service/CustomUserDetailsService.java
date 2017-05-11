package com.ys.app.security.service;

import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.security.CustomUserDetails;
import com.ys.app.security.exception.LoginTrialExceedLimitException;
import com.ys.app.security.exception.UserIsNotLockedException;
import com.ys.app.security.exception.UserIsEnabledException;
import com.ys.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by byun.ys on 4/20/2017.
 */
public class CustomUserDetailsService implements UserDetailsService {

    private static final String	ROLE_PREFIX	= "ROLE_";
    private static final String USERNAME_NOT_FOUND = "Username Not Found";
    private static final String USER_IS_LOCKED = "User is locked";
    private static final String USER_IS_NOT_ENABLED = "User is not enabled";
    private static final String LOGIN_TRIAL_EXCEEDS_LIMIT = "Login trial exceeds limit";
    private static final int MAX_TRIAL = 5;


    private UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email){

        User user=userService.readByEmail(email);

        if(user==null)
            throw new UsernameNotFoundException(USERNAME_NOT_FOUND);

        if(user.isNotLocked()==false)
            throw new UserIsNotLockedException(USER_IS_LOCKED);

        if(user.isEnabled()==false)
            throw new UserIsEnabledException(USER_IS_NOT_ENABLED);

        if(user.getTrial()> MAX_TRIAL)
            throw new LoginTrialExceedLimitException(LOGIN_TRIAL_EXCEEDS_LIMIT);

        return new CustomUserDetails(user,getAuthorities(user));

    }

    public static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Integer roleId=user.getRoleId();
        Set<GrantedAuthority> authorities = new HashSet<>();

        for(Role role:Role.values()){
            if(roleId.equals(roleId)){
                authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX+role.name()));
                break;
            }
        }
        return authorities;
    }

    public  static  void programticSignIn(User user){
        CustomUserDetails customUserDetails=new CustomUserDetails(user,getAuthorities(user));
        Authentication authentication=new UsernamePasswordAuthenticationToken(customUserDetails,user.getPassword(),getAuthorities(user));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public  static User extractUser(Principal principal){
        return ((CustomUserDetails) principal).getUser();
    }
}
