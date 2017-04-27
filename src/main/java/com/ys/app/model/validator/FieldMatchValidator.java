package com.ys.app.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	private String first;
	private String second;
	private String message;

	public void initialize(final FieldMatch constraintAnnotation) {
		this.first = constraintAnnotation.first();
		this.second = constraintAnnotation.second();
		this.message = constraintAnnotation.message();
	}

	@SuppressWarnings("deprecation")
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean r = false;
		try {
			final Object firstObj = BeanUtils.getProperty(value, first);
			final Object secondObj = BeanUtils.getProperty(value, second);

			r = firstObj.equals(secondObj);

		} catch (final Exception ignore) {
		}

		if (r == false) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addNode(second).addConstraintViolation();
		}

		return r;
	}
}
