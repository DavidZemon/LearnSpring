package com.uprr.app.tng.spring.exception;

import javax.annotation.Nonnull;

/**
 * Created by david on 1/21/17.
 */
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 106019300137696214L;

    public BadRequestException(@Nonnull final String message) {
        super(message);
    }
}
