package com.uprr.app.tng.spring.dao;

import com.google.common.collect.ImmutableMap;
import com.uprr.app.tng.spring.pojo.PokemonLocation;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import javax.annotation.Nonnull;

/**
 * Created by david on 1/7/17.
 */
public class PokemonLocationDao {
    @Nonnull private final NamedParameterJdbcOperations jdbcOperations;

    public PokemonLocationDao(@Nonnull final NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void create(@Nonnull final PokemonLocation pokemonLocation) {
        this.jdbcOperations.update("INSERT INTO POKEMON_LOCATION (\n" +
                                       "    X, \n" +
                                       "    Y, \n" +
                                       "    POKEMON_ID\n" +
                                       ") VALUES (\n" +
                                       "    :x,\n" +
                                       "    :y,\n" +
                                       "    :pokemonId\n" +
                                       ")", ImmutableMap.of(
            "x", pokemonLocation.getX(),
            "y", pokemonLocation.getY(),
            "pokemonId", pokemonLocation.getPokemonId()
        ));
    }

    public int getId(final int x, final int y) {
        return this.jdbcOperations.queryForObject("SELECT POKEMON_ID FROM POKEMON_LOCATION\n" +
                                                      "WHERE X = :x and Y = :y", ImmutableMap.of(
            "x", x,
            "y", y
        ), Integer.class);
    }
}
