package com.gleb.springintrodiction.dto;

import org.springframework.http.HttpStatus;

public class ErrorDto {

    private String errorMessage;

    public ErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
