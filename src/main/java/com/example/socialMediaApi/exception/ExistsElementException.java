package com.example.socialMediaApi.exception;

public class ExistsElementException extends RuntimeException {
    public ExistsElementException(String error) {
        super(error);
    }
}
