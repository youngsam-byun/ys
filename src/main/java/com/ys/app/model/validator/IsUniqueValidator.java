package com.ys.app.model.validator;

import com.ys.app.model.User;
import com.ys.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Component
public class IsUniqueValidator implements ConstraintValidator<IsUnique, String> {

	private static final String userPrefix = "user_";

	private String fieldName;

	private UserRepository userRepository;


	@Autowired
	public IsUniqueValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void initialize(IsUnique isUnique) {
		this.fieldName = isUnique.fieldName();
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (fieldName.indexOf(userPrefix) != -1) {
				User user = userRepository.readByEmail(value);
				return user==null;
		}
		else
			return  false;
	}

}
