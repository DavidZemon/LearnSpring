package com.uprr.app.tng.spring.pojo;

import lombok.Data;

import javax.annotation.Nonnull;

/**
 * Created by david on 1/21/17.
 */
@Data
public class ClientError {
    @Nonnull private final String title;
    @Nonnull private final String message;
}
