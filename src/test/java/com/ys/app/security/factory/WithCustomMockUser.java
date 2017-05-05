package com.ys.app.security.factory;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by byun.ys on 4/21/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomUserDetailsSecurityContextFactory.class)
public @interface WithCustomMockUser {
    String username() default "younsam";
    int roleId() default 1;
    int id() default 1;


}
