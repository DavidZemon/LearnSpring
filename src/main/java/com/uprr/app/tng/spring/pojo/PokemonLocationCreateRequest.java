package com.uprr.app.tng.spring.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by david on 1/23/17.
 */
@Data
@NoArgsConstructor
public class PokemonLocationCreateRequest {
    @NotNull(message = "{validation.x.null}")
    private Integer x;
    @NotNull(message = "{validation.y.null}")
    private Integer y;
    @NotNull(message = "{validation.id.null}")
    private Integer pokemonId;
}
