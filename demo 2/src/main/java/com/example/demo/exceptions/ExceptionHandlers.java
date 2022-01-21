package com.example.demo.exceptions;

import com.example.demo.response.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseWrapper handleDuplicateKeyException(DuplicateKeyException duplicateKeyException) {
        return new ResponseWrapper(duplicateKeyException.getResult(), null);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseWrapper handleAccountNotFoundException(AccountNotFoundException accountNotFoundException) {
        return new ResponseWrapper(accountNotFoundException.getResult(), null);
    }
}




