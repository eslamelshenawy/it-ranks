package com.itranks.dbservice.exceptions;

public class ProcessCancelException extends Exception {

    public ProcessCancelException(ErrorCodes msg){
        super(msg.getValue());
    }
}
