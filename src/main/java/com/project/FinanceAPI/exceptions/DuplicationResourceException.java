package com.project.FinanceAPI.exceptions;

public class DuplicationResourceException extends RuntimeException {
    public DuplicationResourceException(String message) {
        super(message);
    }
}
