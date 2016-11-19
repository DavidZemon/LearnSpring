package com.uprr.app.tng.spring.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by david on 1/2/17.
 */
public class PokemonLocation {
    private int x;
    private int y;
    private int pokemonId;

    public PokemonLocation() {
    }

    public PokemonLocation(final int x, final int y, final int pokemonId) {
        this.x = x;
        this.y = y;
        this.pokemonId = pokemonId;
    }

    public int getX() {
        return this.x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public int getPokemonId() {
        return this.pokemonId;
    }

    public void setPokemonId(final int pokemonId) {
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

        final PokemonLocation that = (PokemonLocation) o;

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
