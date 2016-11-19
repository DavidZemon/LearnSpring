package com.uprr.app.tng.spring;

import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.dao.PokemonLocationDao;
import com.uprr.app.tng.spring.factory.PokemonFactory;
import com.uprr.app.tng.spring.pojo.Pokemon;
import com.uprr.app.tng.spring.pojo.PokemonLocation;
import com.uprr.app.tng.spring.random.RandomNumberGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by david on 8/8/16.
 */
public class GameBoard {
    @Nonnull private final PokemonLocationDao    pokemonLocationDao;
    @Nonnull private final PokemonDao            pokemonDao;
    @Nonnull private final PokemonFactory        pokemonFactory;
    @Nonnull private final RandomNumberGenerator randomNumberGenerator;
    private final          int                   size;

    public GameBoard(@Nonnull final PokemonLocationDao pokemonLocationDao,
                     @Nonnull final PokemonDao pokemonDao,
                     @Nonnull final PokemonFactory pokemonFactory,
                     @Nonnull final RandomNumberGenerator randomNumberGenerator,
                     final int size) {
        this.pokemonLocationDao = pokemonLocationDao;
        this.pokemonDao = pokemonDao;
        this.pokemonFactory = pokemonFactory;
        this.randomNumberGenerator = randomNumberGenerator;
        this.size = size;
    }

    public void fillBoard(final int numberOfNewPokemon) {
        for (int index = 0; index < numberOfNewPokemon; index++) {
            final Pokemon pokemon = this.pokemonFactory.build();

            final int x         = this.randomNumberGenerator.generate(this.size);
            final int y         = this.randomNumberGenerator.generate(this.size);
            final int pokemonId = this.pokemonDao.create(pokemon);
            this.pokemonLocationDao.create(new PokemonLocation(x, y, pokemonId));
        }
    }

    @Nullable
    public Pokemon check(final int x, final int y) {
        final Integer pokemonId = this.pokemonLocationDao.getId(x, y);
        if (null == pokemonId) {
            return null;
        } else {
            return this.pokemonDao.get(pokemonId);
        }
    }

    public int getMapArea() {
        return this.size * this.size;
    }
}
