package com.itranks.dbservice.exceptions;

import org.springframework.http.HttpStatus;

public interface BusinessExceptionInterface {
	String getErrorMessage();
	String getErrorCode();
	HttpStatus getHttpStatus();
}
