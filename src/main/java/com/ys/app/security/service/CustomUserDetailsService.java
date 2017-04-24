package com.ys.app.security.service;

import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.security.CustomUserDetails;
import com.ys.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by byun.ys on 4/20/2017.
 */
public class CustomUserDetailsService implements UserDetailsService {

    private static final String	ROLE_PREFIX	= "ROLE_";
    private static final String USERNAME_NOT_FOUND = "Username Not Found";

    private UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user=userService.readByEmail(email);

        if(user==null)
            throw new UsernameNotFoundException(USERNAME_NOT_FOUND);


        CustomUserDetails customUserDetails=new CustomUserDetails(user,getAuthorities(user));
        return null;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Integer roleId=user.getRoleid();
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        for(Role role:Role.values()){
            if(roleId.equals(role.getId())){
                authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX+role.name()));
                break;
            }
        }
        return authorities;
    }
}
