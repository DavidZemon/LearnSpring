package com.uprr.app.tng.spring.dao;

import com.uprr.app.tng.spring.pojo.Pokemon;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by david on 1/7/17.
 */
public class PokemonDao {
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    @Nonnull private final Map<Integer, Pokemon> pokemon = new HashMap<>();

    public int create(@Nonnull final Pokemon pokemon) {
        final int id = idCounter.getAndIncrement();
        pokemon.setId(id);
        this.pokemon.put(id, pokemon);
        return id;
    }

    @Nullable
    public Pokemon get(final int id) {
        return this.pokemon.get(id);
    }
}
