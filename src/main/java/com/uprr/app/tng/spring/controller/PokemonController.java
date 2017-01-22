package com.uprr.app.tng.spring.controller;

import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.pojo.Pokemon;
import com.uprr.app.tng.spring.pojo.PokemonCreateRequest;
import com.uprr.app.tng.spring.pojo.PokemonUpdateRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;

/**
 * Created by david on 1/15/17.
 */
@RestController
@RequestMapping("pokemon")
public class PokemonController {
    @Nonnull private final PokemonDao pokemonDao;

    public PokemonController(@Nonnull final PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }

    @RequestMapping(path = "get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pokemon get(@PathVariable final int id) {
        return this.pokemonDao.get(id);
    }

    @RequestMapping(path = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody @Valid final PokemonUpdateRequest request) {
        this.pokemonDao.update(new Pokemon(request.getId(), request.getHp(), request.getAttack()));
    }

    @RequestMapping(path = "delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable final int id) {
        this.pokemonDao.delete(id);
    }

    @RequestMapping(path = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pokemon create(@RequestBody @Valid final PokemonCreateRequest request) {
        final Pokemon pokemon = new Pokemon(request.getHp(), request.getAttack());
        final int     id      = this.pokemonDao.create(pokemon);
        pokemon.setId(id);
        return pokemon;
    }
}
