package com.uprr.app.tng.spring.exception;

import javax.annotation.Nonnull;

/**
 * Created by david on 1/21/17.
 */
public class MyCustomException extends RuntimeException {
    private static final long serialVersionUID = 3142007755653242365L;

    public MyCustomException(@Nonnull final String message) {
        super(message);
    }
}
