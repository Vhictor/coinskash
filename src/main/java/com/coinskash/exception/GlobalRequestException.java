package com.coinskash.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalRequestException extends RuntimeException {
    private String code;
    private List<Error> errors;
    private Throwable throwable;
    private HttpStatus httpStatus;


//        public GlobalRequestException(String code, String message, Throwable throwable) {
//            super(HttpStatus.valueOf(code),message,throwable);
//            this.code = code;
//            this.throwable = throwable;
//        }

    public GlobalRequestException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }


//    public GlobalExceptionRequest(String message, List<Error> errors, Throwable throwable) {
//        super(message, throwable);
//        this.errors = errors;
//    }
}

