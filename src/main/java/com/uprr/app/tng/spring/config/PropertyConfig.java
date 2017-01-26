package com.uprr.app.tng.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by david on 1/25/17.
 */
@Configuration
@PropertySource("classpath:common.properties")
public class PropertyConfig {
    @Autowired private Environment environment;

    @Bean
    public int mapSize () {
        return this.environment.getRequiredProperty("default-map-size", Integer.class);
    }
}
