package com.example.yandexcase.utils;

import com.example.yandexcase.Error;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class DefaultAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Error> handleNoSuchElementException(NoSuchElementException e) {

      Error response = new Error();
      response.setCode(404);
      response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Error> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        Error response = new Error();
        response.setCode(404);
        response.setMessage("Item not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StructureViolationException.class)
    public ResponseEntity<Error> handleStructureViolationException(StructureViolationException e) {
        Error response = new Error();
        response.setCode(400);
        response.setMessage("Validation Failed");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> handleStructureViolationException(ConstraintViolationException e) {
        Error response = new Error();
        response.setCode(400);
        response.setMessage("Validation Failed");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
