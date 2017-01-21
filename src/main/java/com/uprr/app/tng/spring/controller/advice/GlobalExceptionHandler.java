package com.uprr.app.tng.spring.controller.advice;

import com.uprr.app.tng.spring.exception.MyCustomException;
import com.uprr.app.tng.spring.pojo.ClientError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

/**
 * Created by david on 1/21/17.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MyCustomException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ClientError handleMyCustomException(@Nonnull final MyCustomException e) {
        return new ClientError("Custom error", e.getMessage());
    }
}
