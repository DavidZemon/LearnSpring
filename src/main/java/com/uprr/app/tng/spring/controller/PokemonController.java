package com.uprr.app.tng.spring.controller;

import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.exception.BadRequestException;
import com.uprr.app.tng.spring.pojo.Pokemon;
import com.uprr.app.tng.spring.pojo.PokemonCreateRequest;
import com.uprr.app.tng.spring.pojo.PokemonUpdateRequest;
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

    @RequestMapping(path = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody final PokemonUpdateRequest request) {
        if (null == request.getId()) {
            throw new BadRequestException("Pokemon ID cannot be null");
        } else if (null == request.getHp()) {
            throw new BadRequestException("Pokemon HP cannot be null");
        } else if (1 > request.getHp() || 100 < request.getHp()) {
            throw new BadRequestException("Pokemon HP must be between 1 and 100 (inclusive)");
        } else if (null == request.getAttack()) {
            throw new BadRequestException("Pokemon attack cannot be null");
        } else if (1 > request.getAttack()) {
            throw new BadRequestException("Pokemon attack must be greater than 0");
        } else {
            this.pokemonDao.update(new Pokemon(request.getId(), request.getHp(), request.getAttack()));
        }
    }

    @RequestMapping(path = "delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable final int id) {
        this.pokemonDao.delete(id);
    }

    @RequestMapping(path = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pokemon create(@RequestBody final PokemonCreateRequest request) {
        if (null == request.getHp()) {
            throw new BadRequestException("Pokemon HP cannot be null");
        } else if (1 > request.getHp() || 100 < request.getHp()) {
            throw new BadRequestException("Pokemon HP must be between 1 and 100 (inclusive)");
        } else if (null == request.getAttack()) {
            throw new BadRequestException("Pokemon attack cannot be null");
        } else if (1 > request.getAttack()) {
            throw new BadRequestException("Pokemon attack must be greater than 0");
        } else {
            final Pokemon pokemon = new Pokemon(request.getHp(), request.getAttack());
            final int     id      = this.pokemonDao.create(pokemon);
            pokemon.setId(id);
            return pokemon;
        }
    }
}
