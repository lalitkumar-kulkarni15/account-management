package com.account.management.exception;

public class AccountManagementException extends RuntimeException {

    public AccountManagementException(String message){
        super(message);
    }

    public AccountManagementException(String message, Exception exception){
        super(message, exception);
    }
}
