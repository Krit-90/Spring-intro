package com.gleb.springintrodiction.dto;

import org.springframework.http.HttpStatus;

public class ErrorDto {

    private HttpStatus errorMessage;

    public ErrorDto(HttpStatus errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getErrorMessage() {
        return errorMessage;
    }
}