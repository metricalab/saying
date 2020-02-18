package com.metricalab.saying.exception;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 3547062482344837917L;

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<String> customError(final DataBaseException ex, final WebRequest request) {

		final String error = new JSONObject()
				.put("Error", new JSONObject().put("CodeError", ex.getCode()).put("Description Error", ex.getMessage()))
				.toString();
		return new ResponseEntity<>(error, ex.getStatus());
	}

}
