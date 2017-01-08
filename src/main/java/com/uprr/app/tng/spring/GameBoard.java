package com.uprr.app.tng.spring;

import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.dao.PokemonLocationDao;
import com.uprr.app.tng.spring.pojo.Pokemon;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by david on 8/8/16.
 */
public class GameBoard {
    @Nonnull private final PokemonLocationDao    pokemonLocationDao;
    @Nonnull private final PokemonDao            pokemonDao;

    public GameBoard(@Nonnull final PokemonLocationDao pokemonLocationDao,
                     @Nonnull final PokemonDao pokemonDao) {
        this.pokemonLocationDao = pokemonLocationDao;
        this.pokemonDao = pokemonDao;
    }

    @Nullable
    public Pokemon check(final int x, final int y) {
        try {
            final int pokemonId = this.pokemonLocationDao.getId(x, y);
            return this.pokemonDao.get(pokemonId);
        } catch (final EmptyResultDataAccessException ignored) {
            return null;
        }
    }
}
