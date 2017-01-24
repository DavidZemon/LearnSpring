package com.uprr.app.tng.spring.validation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by david on 1/23/17.
 */
public class IntegerRangeValidator implements ConstraintValidator<IntegerRange, Number> {

    private long min;
    private long max;

    public void initialize(final IntegerRange constraint) {
        this.min = constraint.min();
        this.max = constraint.max();
    }

    public boolean isValid(@Nullable final Number candidate, @Nonnull final ConstraintValidatorContext context) {
        return null == candidate || (this.min <= candidate.longValue() && candidate.longValue() <= this.max);
    }
}
