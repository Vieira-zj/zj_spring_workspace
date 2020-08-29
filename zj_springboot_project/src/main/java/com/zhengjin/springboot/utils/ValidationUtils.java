package com.zhengjin.springboot.utils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public final class ValidationUtils {

	private static final Validator validator;

	static {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	static public Validator getValidator() {
		return validator;
	}

}
