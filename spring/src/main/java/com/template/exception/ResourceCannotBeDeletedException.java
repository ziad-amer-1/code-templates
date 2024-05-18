package com.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceCannotBeDeletedException extends RuntimeException {
    public ResourceCannotBeDeletedException(String message) {
        super(message);
    }
}