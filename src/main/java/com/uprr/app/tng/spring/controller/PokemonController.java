package com.uprr.app.tng.spring.controller;

import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.pojo.Pokemon;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;

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
}
