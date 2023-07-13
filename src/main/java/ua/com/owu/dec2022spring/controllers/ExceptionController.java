package ua.com.owu.dec2022spring.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> blankNameException(MethodArgumentNotValidException e) {
        String message = e.getFieldError().getDefaultMessage();
        System.out.println(message);

        return new ResponseEntity<>(message, HttpStatusCode.valueOf(500));

    }
}
