package com.uprr.app.tng.spring.random;

/**
 * Created by david on 8/8/16.
 */
public class RandomNumberGenerator {
    public int generate(final int max) {
        return (int) (Math.random() * max);
    }
}
