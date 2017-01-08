package com.uprr.app.tng.spring.factory;

import com.uprr.app.tng.spring.pojo.Pokemon;
import com.uprr.app.tng.spring.random.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;

/**
 * Created by david on 8/8/16.
 */
public class PokemonFactory {
    @Autowired
    @Nonnull
    private RandomNumberGenerator randomNumberGenerator;

    @Nonnull
    public Pokemon build() {
        final int hp     = this.randomNumberGenerator.generate(100);
        final int attack = this.randomNumberGenerator.generate(10);
        return new Pokemon(hp, attack);
    }
}
