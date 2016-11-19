package com.uprr.app.tng.spring.config;

import com.uprr.app.tng.spring.GameBoard;
import com.uprr.app.tng.spring.hmi.HumanInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Scanner;

/**
 * Created by david on 11/19/16.
 */
@Configuration
@ImportResource("classpath:spring-context.xml")
public class MainConfig {
    @Bean
    public HumanInterface mapInteraction(final Scanner scanner,
                                         final GameBoard gameBoard) {
        return new HumanInterface(scanner, System.out, gameBoard);
    }
}
