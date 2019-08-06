package com.order.until;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.order.until.exception.BusinessException;


public class ValidatorUtils {
private static Validator validator;
	
	static {
		validator =Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	//第二种方式创建Validator
//	static {
//		ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
//		        .configure()
//		        .failFast(true)
//		        .buildValidatorFactory();
//	 validator = validatorFactory.getValidator();
//	}
	
	/**
	 * 
	 * @param object
	 * @param groups
	 * @throws BusinessException 验证失败抛出的异常
	 */
	public static void validateEntity(Object object,Class<?>... groups) throws BusinessException {
		Set<ConstraintViolation<Object>> validate = validator.validate(object, groups);
		if(!validate.isEmpty()) {
			ConstraintViolation<Object> constraint = validate.iterator().next();
			throw new BusinessException(constraint.getMessage());
		}
		
	}
}
