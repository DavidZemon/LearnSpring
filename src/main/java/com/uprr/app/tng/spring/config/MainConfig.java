package com.uprr.app.tng.spring.config;

import com.uprr.app.tng.spring.GameBoard;
import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.dao.PokemonLocationDao;
import com.uprr.app.tng.spring.factory.PokemonFactory;
import com.uprr.app.tng.spring.hmi.HumanInterface;
import com.uprr.app.tng.spring.random.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

/**
 * Created by david on 11/19/16.
 */
@Configuration
@PropertySource("classpath:common.properties")
public class MainConfig {
    @Bean
    public HumanInterface mapInteraction(final Scanner scanner,
                                         final GameBoard gameBoard) {
        return new HumanInterface(scanner, System.out, gameBoard);
    }

    @Bean
    public RandomNumberGenerator randomNumberGenerator() {
        return new RandomNumberGenerator();
    }

    @Bean
    public PokemonDao pokemonDao () {
        return new PokemonDao();
    }

    @Bean
    public PokemonLocationDao pokemonLocationDao () {
        return new PokemonLocationDao();
    }

    @Bean
    public PokemonFactory pokemonFactory() {
        return new PokemonFactory(this.randomNumberGenerator());
    }


    @Bean
    public GameBoard gameBoard(final PokemonFactory pokemonFactory,
                               final RandomNumberGenerator randomNumberGenerator,
                               @Value("${default-map-size}") final int mapSize) {
        return new GameBoard(this.pokemonLocationDao(), this.pokemonDao(), pokemonFactory, randomNumberGenerator,
                             mapSize);
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
