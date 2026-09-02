package com.project.FinanceAPI.exceptions;

public class UserNotSavedException extends RuntimeException {
    public UserNotSavedException(String message) {
        super(message);
    }

    public UserNotSavedException(){
        super("User not saved correctly!");
    }
}
