package com.uprr.app.tng.spring.config;

import com.uprr.app.tng.spring.GameBoard;
import com.uprr.app.tng.spring.hmi.HumanInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Scanner;

/**
 * Created by david on 11/19/16.
 */
@Configuration
@Import({DaoConfig.class, PropertyConfig.class})
public class MainConfig {
    @Autowired private DaoConfig daoConfig;

    @Bean
    public HumanInterface mapInteraction() {
        return new HumanInterface(this.scanner(), System.out, this.gameBoard());
    }

    @Bean
    public GameBoard gameBoard() {
        return new GameBoard(this.daoConfig.pokemonLocationDao(), this.daoConfig.pokemonDao());
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
