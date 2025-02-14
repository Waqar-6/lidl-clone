package com.wfarooq.inventorymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String resource, String field, String value) {
        super(String.format("%s already exists with %s:'%s'", resource, field, value));
    }
}
