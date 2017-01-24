package com.uprr.app.tng.spring.controller;

import com.uprr.app.tng.spring.dao.PokemonLocationDao;
import com.uprr.app.tng.spring.pojo.PokemonId;
import com.uprr.app.tng.spring.pojo.PokemonLocation;
import com.uprr.app.tng.spring.pojo.PokemonLocationCreateRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;

/**
 * Created by david on 1/23/17.
 */
@RestController
@RequestMapping("pokemon-location")
public class PokemonLocationController {
    @Nonnull private final PokemonLocationDao pokemonLocationDao;

    public PokemonLocationController(@Nonnull final PokemonLocationDao pokemonLocationDao) {
        this.pokemonLocationDao = pokemonLocationDao;
    }

    @ApiOperation("Determine which Pokemon (if any) exists at the requested location")
    @RequestMapping(path = "get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PokemonId get (@RequestParam final int x, @RequestParam final int y) {
        return new PokemonId(this.pokemonLocationDao.getId(x, y));
    }

    @ApiOperation("Associate an X/Y coordinate with a specific Pokemon")
    @RequestMapping(path = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody @Valid final PokemonLocationCreateRequest request) {
        this.pokemonLocationDao.create(new PokemonLocation(request.getX(), request.getY(), request.getPokemonId()));
    }
}
