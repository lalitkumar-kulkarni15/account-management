package com.account.management.exception;

public class AccountManagementValidationException extends RuntimeException {

    public AccountManagementValidationException(String message){
        super(message);
    }

    public AccountManagementValidationException(String message, Exception exception){
        super(message, exception);
    }

}
