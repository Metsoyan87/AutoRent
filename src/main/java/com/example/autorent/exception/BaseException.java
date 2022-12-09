package com.example.autorent.exception;


import lombok.Data;

@Data
public class BaseException extends RuntimeException{

    protected final Error error;

    public BaseException(Error error) {
        this.error = error;
    }
}
