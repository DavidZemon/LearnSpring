package com.uprr.app.tng.spring.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;

/**
 * Created by david on 1/23/17.
 */
public class PokemonLocationCreateRequest {
    @NotNull(message = "{validation.x.null}")
    private Integer x;
    @NotNull(message = "{validation.y.null}")
    private Integer y;
    @NotNull(message = "{validation.id.null}")
    private Integer pokemonId;

    public Integer getX() {
        return this.x;
    }

    public void setX(final Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(final Integer y) {
        this.y = y;
    }

    public Integer getPokemonId() {
        return this.pokemonId;
    }

    public void setPokemonId(final Integer pokemonId) {
        this.pokemonId = pokemonId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        final PokemonLocationCreateRequest that = (PokemonLocationCreateRequest) o;

        return new EqualsBuilder()
            .append(this.x, that.x)
            .append(this.y, that.y)
            .append(this.pokemonId, that.pokemonId)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(this.x)
            .append(this.y)
            .append(this.pokemonId)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("x", this.x)
            .append("y", this.y)
            .append("pokemonId", this.pokemonId)
            .toString();
    }
}
