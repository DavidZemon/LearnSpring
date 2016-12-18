package com.uprr.app.tng.spring.dao;

import com.uprr.app.tng.spring.pojo.Pokemon;
import org.springframework.jdbc.core.JdbcOperations;

import javax.annotation.Nonnull;

/**
 * Created by david on 12/5/16.
 */
public class PokemonDao {
    @Nonnull private final JdbcOperations jdbcOperations;

    public PokemonDao(@Nonnull final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public int create(@Nonnull final Pokemon pokemon) {
        final int id = this.jdbcOperations.queryForObject("SELECT POKEMON_ID_SEQ.nextval FROM DUAL", Integer.class);
        pokemon.setId(id);
        this.jdbcOperations.update("INSERT INTO POKEMON (ID, HP, ATTACK) VALUES (?, ?, ?)",
                                   id, pokemon.getHp(), pokemon.getAttack());
        return id;
    }

    @Nonnull
    public Pokemon get(final int requestedId) {
        return this.jdbcOperations.queryForObject("SELECT ID, HP, ATTACK FROM POKEMON WHERE ID = ?",
                                                  new Object[]{requestedId}, (rs, rowNum) -> {
                final int id = rs.getInt("ID");
                final int hp  = rs.getInt("HP");
                final int attack  = rs.getInt("ATTACK");
                return new Pokemon(id, hp, attack);
            });
    }

    public void update(@Nonnull final Pokemon pokemon) {
        this.jdbcOperations.update("UPDATE POKEMON SET HP = ?, ATTACK = ? WHERE ID = ?",
                                   pokemon.getHp(), pokemon.getAttack(), pokemon.getId());
    }

    public void delete(final int id) {
        this.jdbcOperations.update("DELETE FROM POKEMON WHERE ID = ?", id);
    }
}
