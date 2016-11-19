package com.uprr.app.tng.spring;

import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.dao.PokemonLocationDao;
import com.uprr.app.tng.spring.factory.PokemonFactory;
import com.uprr.app.tng.spring.hmi.HumanInterface;
import com.uprr.app.tng.spring.random.RandomNumberGenerator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by david on 8/8/16.
 */
public class Main {
    public static void main(final String... args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml")) {
            final Integer               defaultMapSize        = context.getBean("defaultMapSize", Integer.class);
            final RandomNumberGenerator randomNumberGenerator = context.getBean(RandomNumberGenerator.class);
            final PokemonDao            pokemonDao            = new PokemonDao();
            final PokemonLocationDao    pokemonLocationDao    = new PokemonLocationDao();
            final PokemonFactory        pokemonFactory        = new PokemonFactory(randomNumberGenerator);
            final GameBoard gameBoard = new GameBoard(pokemonLocationDao, pokemonDao, pokemonFactory,
                                                      randomNumberGenerator, defaultMapSize);
            final InputStream input  = System.in;
            final PrintStream output = System.out;

            try (Scanner scanner = new Scanner(input)) {
                new HumanInterface(scanner, output, gameBoard).run();
            }
        }
    }
}
