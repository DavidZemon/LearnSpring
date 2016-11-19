package com.uprr.app.tng.spring;

import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.dao.PokemonLocationDao;
import com.uprr.app.tng.spring.factory.PokemonFactory;
import com.uprr.app.tng.spring.hmi.HumanInterface;
import com.uprr.app.tng.spring.random.RandomNumberGenerator;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by david on 8/8/16.
 */
public class Main {
    private static final int DEFAULT_MAP_SIZE = 3;

    public static void main(final String... args) {
        final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        final PokemonDao            pokemonDao            = new PokemonDao();
        final PokemonLocationDao    pokemonLocationDao    = new PokemonLocationDao();
        final PokemonFactory        pokemonFactory        = new PokemonFactory(randomNumberGenerator);
        final GameBoard gameBoard = new GameBoard(pokemonLocationDao, pokemonDao, pokemonFactory, randomNumberGenerator,
                                                  DEFAULT_MAP_SIZE);
        final InputStream input  = System.in;
        final PrintStream output = System.out;

        try (Scanner scanner = new Scanner(input)) {
            new HumanInterface(scanner, output, gameBoard).run();
        }
    }
}
