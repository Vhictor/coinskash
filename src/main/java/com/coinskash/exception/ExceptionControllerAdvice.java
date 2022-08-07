package com.coinskash.exception;

import com.coinskash.model.ResponseDataFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ResponseDataFormat> handleInvalidTokenException (InvalidTokenException invalidTokenException){
        ResponseDataFormat ResponseDataFormat = new ResponseDataFormat(invalidTokenException.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(ResponseDataFormat, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ResponseDataFormat> handleInvalidData (InvalidDataException invalidDataException){
        ResponseDataFormat ResponseDataFormat = new ResponseDataFormat(invalidDataException.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(ResponseDataFormat, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler (DataNotFoundException.class)
    public ResponseEntity<ResponseDataFormat> handleDataNotFoundException (DataNotFoundException dataNotFoundException){
        ResponseDataFormat ResponseDataFormat = new ResponseDataFormat(dataNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(ResponseDataFormat, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseDataFormat ResponseDataFormat = new ResponseDataFormat(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(ResponseDataFormat, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value
            = {GlobalRequestException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        ResponseDataFormat response = new ResponseDataFormat();
        response.setStatus(((GlobalRequestException) ex).getHttpStatus());
        response.setMessage(((GlobalRequestException) ex).getMessage());
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, response,
                new HttpHeaders(), ((GlobalRequestException) ex).getHttpStatus(), request);
    }
}
