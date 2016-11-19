package com.uprr.app.tng.spring.dao;

import com.uprr.app.tng.spring.pojo.PokemonLocation;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by david on 1/7/17.
 */
public class PokemonLocationDao {
    @Nonnull private final Collection<PokemonLocation> locations = new ArrayList<>();

    public void create(@Nonnull final PokemonLocation pokemonLocation) {
        this.locations.add(pokemonLocation);
    }

    public Integer getId(final int x, final int y) {
        return this.locations.stream()
                             .filter(location -> location.getX() == x && location.getY() == y)
                             .map(PokemonLocation::getPokemonId)
                             .findFirst()
                             .orElse(null);
    }
}
