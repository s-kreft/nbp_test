package com.example.nbp_test.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class NbpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleAll(HttpClientErrorException ex, WebRequest request) {
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<String>("Not found.", HttpStatus.NOT_FOUND);
        } else if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return new ResponseEntity<String>("Bad params.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Unable to process request " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex, WebRequest request) {
        return new ResponseEntity<String>("Unable to process request " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
