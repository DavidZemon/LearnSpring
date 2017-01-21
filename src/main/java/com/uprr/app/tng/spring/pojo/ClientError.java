package com.uprr.app.tng.spring.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nonnull;

/**
 * Created by david on 1/21/17.
 */
public class ClientError {
    @Nonnull private final String title;
    @Nonnull private final String message;

    public ClientError(@Nonnull final String title,
                       @Nonnull final String message) {
        this.title = title;
        this.message = message;
    }

    @Nonnull
    public String getTitle() {
        return this.title;
    }

    @Nonnull
    public String getMessage() {
        return this.message;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        final ClientError that = (ClientError) o;

        return new EqualsBuilder()
            .append(this.title, that.title)
            .append(this.message, that.message)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(this.title)
            .append(this.message)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("title", this.title)
            .append("message", this.message)
            .toString();
    }
}
