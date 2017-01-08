package com.uprr.app.tng.spring.random;

import org.springframework.stereotype.Component;

/**
 * Created by david on 8/8/16.
 */
@Component
public class RandomNumberGenerator {
    public int generate(final int max) {
        return (int) (Math.random() * max);
    }
}
