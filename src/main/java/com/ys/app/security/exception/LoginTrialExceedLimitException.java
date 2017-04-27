package com.ys.app.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by byun.ys on 4/26/2017.
 */
public class LoginTrialExceedLimitException extends AuthenticationException {
    public LoginTrialExceedLimitException(String msg) {
        super(msg);
    }
}
