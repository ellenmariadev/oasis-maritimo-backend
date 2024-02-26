package com.example.oasismaritimo.exceptions.core;

import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

public class ErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    private int statusCode;

    private String message;

    public ErrorResponse(){};

    public int getStatusCode() {
        return statusCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
