package com.uprr.app.tng.spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by david on 1/2/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonLocation {
    private int x;
    private int y;
    private int pokemonId;
}
