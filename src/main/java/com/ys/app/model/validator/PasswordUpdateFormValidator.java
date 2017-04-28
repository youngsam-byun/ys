package com.ys.app.model.validator;


import com.ys.app.model.validator.form.PasswordUpdateForm;
import com.ys.app.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by byun.ys on 4/27/2017.
 */
@Component
public class PasswordUpdateFormValidator implements Validator {

    @Value("${passwordUpdateValidator.oldPassword.empty")
    private static final String PASSWORD_UPDATE_VALIDATOR_OLD_PASSWORD_EMPTY = "passwordUpdateValidator.oldPassword.empty";
    @Value("${passwordUpdateValidator.password.empty")
    private static final String PASSWORD_UPDATE_VALIDATOR_PASSWORD_EMPTY = "passwordUpdateValidator.password.empty";
    @Value("${passwordUpdateValidator.passwordConfirm.empty")
    private static final String PASSWORD_UPDATE_VALIDATOR_PASSWORD_CONFIRM_EMPTY = "passwordUpdateValidator.passwordConfirm.empty";
    @Value("${passwordUpdateValidator.oldPassword.wrong")
    private static final String OLD_PASSWORD_PASSWORD_IS_WRONG = "oldPassword password is Wrong";
    @Value("${passwordUpdateValidator.password.notMatch")
    private static final String PASSWORD_AND_PASSWORD_CONFIRM_DO_NOT_MATCH = "password and passwordConfirm do not match";

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private static boolean matchWithCurrentPassword(String currPassword) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.isAuthenticated() == false)
            return false;

        String encodedPassword = ((CustomUserDetails) authentication.getPrincipal()).getUser().getPassword();
        return bCryptPasswordEncoder.matches(currPassword, encodedPassword);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordUpdateForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        PasswordUpdateForm passwordUpdateForm = (PasswordUpdateForm) target;
        String oldPassword = passwordUpdateForm.getOldPassword();
        String password = passwordUpdateForm.getPassword();
        String passwordConfirm = passwordUpdateForm.getPasswordConfirm();

        ValidationUtils.rejectIfEmpty(errors, "oldPassword", PASSWORD_UPDATE_VALIDATOR_OLD_PASSWORD_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "password", PASSWORD_UPDATE_VALIDATOR_PASSWORD_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "passwordConfirm", PASSWORD_UPDATE_VALIDATOR_PASSWORD_CONFIRM_EMPTY);

        if (matchWithCurrentPassword(oldPassword) == false)
            errors.rejectValue("oldPassword", OLD_PASSWORD_PASSWORD_IS_WRONG);

        if (password.equals(passwordConfirm) == false)
            errors.rejectValue("passwordConfirm", PASSWORD_AND_PASSWORD_CONFIRM_DO_NOT_MATCH);

    }
}
