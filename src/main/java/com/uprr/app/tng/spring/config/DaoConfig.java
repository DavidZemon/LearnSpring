package com.uprr.app.tng.spring.config;

import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.dao.PokemonLocationDao;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by david on 1/1/17.
 */
@Configuration
public class DaoConfig {
    @Autowired private PropertyConfig propertyConfig;

    @Bean
    public PokemonDao pokemonDao() {
        return new PokemonDao(this.jdbcOperations());
    }

    @Bean
    public PokemonLocationDao pokemonLocationDao() {
        return new PokemonLocationDao();
    }

    @Bean
    public NamedParameterJdbcOperations jdbcOperations() {
        return new NamedParameterJdbcTemplate(this.dataSource());
    }

    @Bean
    public DataSource dataSource() {
        final JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl(String.format("%s;SCHEMA=%s", this.propertyConfig.databaseUrl(),
                                        this.propertyConfig.databaseUser()));
        dataSource.setUser(this.propertyConfig.databaseUser());
        dataSource.setPassword(this.propertyConfig.databasePassword());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }
}
