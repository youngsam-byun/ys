package com.ys.app.security.factory;

import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.repo.impl.JdbcUserRepositoryImpl;
import com.ys.app.security.CustomUserDetails;
import com.ys.app.security.service.CustomUserDetailsService;
import com.ys.app.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.test.context.support.WithUserDetails;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by byun.ys on 4/21/2017.
 */
public final class WithCustomUserDetailsSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser>{


    @Override
    public SecurityContext createSecurityContext(WithCustomMockUser withCustomMockUser) {


        User user=new User();
        user.setRoleId(9);
        user.setId(1);
        SecurityContext context = SecurityContextHolder.createEmptyContext();


        CustomUserDetails principal =
                new CustomUserDetails(user,getAuthorities(user));
        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());

        context.setAuthentication(auth);

        return context;

    }


    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Integer roleId=user.getRoleId();
        Set<GrantedAuthority> authorities = new HashSet<>();

        for(Role role:Role.values()){
            if(roleId.equals(role.getId())){
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
                break;
            }
        }
        return authorities;
    }
}
