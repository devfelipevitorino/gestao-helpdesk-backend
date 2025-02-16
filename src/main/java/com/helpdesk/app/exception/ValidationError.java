package com.helpdesk.app.exception;

import java.util.ArrayList;
import java.util.List;

import com.helpdesk.app.controller.exceptions.StandardError;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error, String mensage, String path) {
		super(timestamp, status, error, mensage, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void setErrors(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
