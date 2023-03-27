package com.kourchenko.fileoptimize.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AmazonException extends RuntimeException {

    private static final long serialVersionUID = -4834752686758321850L;

    public AmazonException(String message) {
        super(message);
    }

    public AmazonException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
