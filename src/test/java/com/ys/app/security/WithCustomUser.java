package com.ys.app.security;

import org.springframework.security.test.context.support.WithSecurityContext;

/**
 * Created by byun.ys on 4/21/2017.
 */
@WithSecurityContext(factory = WithCustomUserDetailsSecurityContextFactory.class)
public @interface WithCustomUser {
    String username() default "rob";
    String name() default "rob Winch";
}
