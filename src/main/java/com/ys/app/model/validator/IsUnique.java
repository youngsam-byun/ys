package com.ys.app.model.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsUniqueValidator.class)

public @interface IsUnique {

	String message() default "Already exists";

	@SuppressWarnings("rawtypes")
	Class[]groups() default {};

	@SuppressWarnings("rawtypes")
	Class[]payload() default {};

	String fieldName();

}
