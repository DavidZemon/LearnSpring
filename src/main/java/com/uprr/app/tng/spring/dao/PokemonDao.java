package com.uprr.app.tng.spring.dao;

import com.google.common.collect.ImmutableMap;
import com.uprr.app.tng.spring.pojo.Pokemon;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import javax.annotation.Nonnull;
import java.util.Collections;

/**
 * Created by david on 12/5/16.
 */
public class PokemonDao {
    @Nonnull private final NamedParameterJdbcOperations jdbcOperations;

    public PokemonDao(@Nonnull final NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public int create(@Nonnull final Pokemon pokemon) {
        final int id = this.jdbcOperations.queryForObject("SELECT POKEMON_ID_SEQ.nextval FROM DUAL",
                                                          Collections.emptyMap(), Integer.class);
        pokemon.setId(id);
        this.jdbcOperations.update("INSERT INTO POKEMON (ID, HP, ATTACK) VALUES (:id, :hp, :attack)", ImmutableMap.of(
            "id", id,
            "hp", pokemon.getHp(),
            "attack", pokemon.getAttack()
        ));
        return id;
    }

    @Nonnull
    public Pokemon get(final int requestedId) {
        return this.jdbcOperations.queryForObject("SELECT ID, HP, ATTACK FROM POKEMON WHERE ID = :id",
                                                  ImmutableMap.of("id", requestedId), (rs, rowNum) -> {
                final int id     = rs.getInt("ID");
                final int hp     = rs.getInt("HP");
                final int attack = rs.getInt("ATTACK");
                return new Pokemon(id, hp, attack);
            });
    }

    public void update(@Nonnull final Pokemon pokemon) {
        this.jdbcOperations.update("UPDATE POKEMON SET HP = :hp, ATTACK = :attack WHERE ID = :id", ImmutableMap.of(
            "hp", pokemon.getHp(),
            "attack", pokemon.getAttack(),
            "id", pokemon.getId()
        ));
    }

    public void delete(final int id) {
        this.jdbcOperations.update("DELETE FROM POKEMON WHERE ID = :id", ImmutableMap.of("id", id));
    }
}
