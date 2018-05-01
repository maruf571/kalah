package com.maruf.kalah.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {

    private final String message;
    private final long timestamp;
    private final HttpStatus status;

    public ExceptionResponse(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public static ExceptionResponse of(final String message,
                                       HttpStatus status){
        return new ExceptionResponse(message, status);
    }

}
