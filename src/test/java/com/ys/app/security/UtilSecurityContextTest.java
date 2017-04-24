package com.ys.app.security;

import com.ys.app.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * Created by byun.ys on 4/22/2017.
 */
public class UtilSecurityContextTest {

    public static SecurityContext returnSecurityContext(User user,int roleId) {

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("userName", "password");

        // Authenticate the user
        Authentication authentication = new Authentication() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {

                user.setRoleid(roleId);
                return user;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }
        };

        authRequest.setDetails(user);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        return securityContext;
    }
}
