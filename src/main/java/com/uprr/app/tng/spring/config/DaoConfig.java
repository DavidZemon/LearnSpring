package com.uprr.app.tng.spring.config;

import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.dao.PokemonLocationDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by david on 1/25/17.
 */
@Configuration
public class DaoConfig {
    @Bean
    public PokemonDao pokemonDao () {
        return new PokemonDao();
    }

    @Bean
    public PokemonLocationDao pokemonLocationDao () {
        return new PokemonLocationDao();
    }
}
