package com.metricalab.saying.exception;

import org.springframework.http.HttpStatus;

public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = 3018685477571858578L;
	final String code;
	final HttpStatus status;

	public DataBaseException(final String code, final String message, final HttpStatus status) {
		super(message);
		this.code = code;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public HttpStatus getStatus() {
		return status;
	}

}