package com.example.autorent.exception;

import lombok.Data;

public class EntityNotFoundException extends BaseException{
    public EntityNotFoundException(Error error) {
        super(error);
    }


}
