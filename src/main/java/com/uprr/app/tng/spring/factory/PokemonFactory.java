package com.uprr.app.tng.spring.factory;

import com.uprr.app.tng.spring.pojo.Pokemon;
import com.uprr.app.tng.spring.random.RandomNumberGenerator;

import javax.annotation.Nonnull;

/**
 * Created by david on 8/8/16.
 */
public class PokemonFactory {
    @Nonnull private final RandomNumberGenerator randomNumberGenerator;

    public PokemonFactory(@Nonnull final RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Nonnull
    public Pokemon build() {
        final int hp     = this.randomNumberGenerator.generate(100);
        final int attack = this.randomNumberGenerator.generate(10);
        return new Pokemon(hp, attack);
    }
}
