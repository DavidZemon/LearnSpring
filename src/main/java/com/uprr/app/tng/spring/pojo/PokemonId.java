package com.uprr.app.tng.spring.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by david on 1/23/17.
 */
public class PokemonId {
    private final int pokemonId;

    public PokemonId(final int id) {
        this.pokemonId = id;
    }

    public int getPokemonId() {
        return this.pokemonId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        final PokemonId pokemonId1 = (PokemonId) o;

        return new EqualsBuilder()
            .append(this.pokemonId, pokemonId1.pokemonId)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(this.pokemonId)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("pokemonId", this.pokemonId)
            .toString();
    }
}
