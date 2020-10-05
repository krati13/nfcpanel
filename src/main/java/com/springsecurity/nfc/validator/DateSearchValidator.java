package com.springsecurity.nfc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springsecurity.nfc.constants.Constants;
import com.springsecurity.nfc.model.DateSearch;


public class DateSearchValidator implements Validator, Constants {

	@Override
	public boolean supports(Class<?> clazz) {
		return DateSearch.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DateSearch ds = (DateSearch) target;
		if (ds.getCreated_from()==null || ds.getCreated_from().equalsIgnoreCase(EMPTY_STRING))
			errors.rejectValue(CREATED_FROM, "NULL", "Validation Error :: Create From date is empty");
		
		if (ds.getCreated_to()==null || ds.getCreated_to().equalsIgnoreCase(EMPTY_STRING))
			errors.rejectValue(CREATED_TO, "NULL", "Validation Error :: Create To date is empty");
	}
	
}
