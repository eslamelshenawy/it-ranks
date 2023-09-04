package com.itranks.selfservices.exceptions;

public class ProcessCancelException extends Exception {

    public ProcessCancelException(ErrorCodes msg){
        super(msg.getValue());
    }
}
