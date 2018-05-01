package com.maruf.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class KalahExceptionHandler extends ResponseEntityExceptionHandler{


    @ExceptionHandler(KalahException.class)
    public final ResponseEntity handleKalahException(final KalahException e){
        ExceptionResponse response = ExceptionResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @ExceptionHandler(KalahIllegalMoveException.class)
    public final ResponseEntity handleIllegalMove(final KalahIllegalMoveException e){
        ExceptionResponse response = ExceptionResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
