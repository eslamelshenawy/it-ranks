package com.itranks.selfservices.exceptions;

import org.springframework.http.HttpStatus;

public interface BusinessExceptionInterface {
	String getErrorMessage();
	String getErrorCode();
	HttpStatus getHttpStatus();
}
