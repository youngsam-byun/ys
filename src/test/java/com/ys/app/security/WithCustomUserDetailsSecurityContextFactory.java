package com.ys.app.security;

import com.ys.app.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.test.context.support.WithUserDetails;

/**
 * Created by byun.ys on 4/21/2017.
 */
public final class WithCustomUserDetailsSecurityContextFactory implements WithSecurityContextFactory<WithUserDetails>{


    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public WithCustomUserDetailsSecurityContextFactory(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public SecurityContext createSecurityContext(WithUserDetails withUserDetails) {

        String email=withUserDetails.value();
        CustomUserDetails pripcipal = (CustomUserDetails)customUserDetailsService.loadUserByUsername(email);
        Authentication authentication=new UsernamePasswordAuthenticationToken(pripcipal,pripcipal.getPassword(),pripcipal.getAuthorities());
        SecurityContext context= SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;

    }
}
