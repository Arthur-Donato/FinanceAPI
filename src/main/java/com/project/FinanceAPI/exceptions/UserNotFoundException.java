package com.project.FinanceAPI.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(){
        super("This user was not found.");
    }
}
