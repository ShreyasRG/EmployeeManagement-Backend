package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
