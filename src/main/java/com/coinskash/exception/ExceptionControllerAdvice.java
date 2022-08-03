package com.coinskash.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = {InvalidTokenException.class})
    public ResponseEntity<ExceptionMessageFormat> handleInvalidTokenException (InvalidTokenException invalidTokenException){
        ExceptionMessageFormat exceptionMessageFormat = new ExceptionMessageFormat(invalidTokenException.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(exceptionMessageFormat, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidDataException.class})
    public ResponseEntity<ExceptionMessageFormat> handleInvalidData (InvalidDataException invalidDataException){
        ExceptionMessageFormat exceptionMessageFormat = new ExceptionMessageFormat(invalidDataException.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(exceptionMessageFormat, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<ExceptionMessageFormat> handleDataNotFoundException (DataNotFoundException dataNotFoundException){
        ExceptionMessageFormat exceptionMessageFormat = new ExceptionMessageFormat(dataNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(exceptionMessageFormat, HttpStatus.BAD_REQUEST);
    }

}
