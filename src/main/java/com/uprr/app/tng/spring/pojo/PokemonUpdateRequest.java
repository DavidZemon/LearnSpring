package com.uprr.app.tng.spring.pojo;

import com.uprr.app.tng.spring.validation.Attack;
import com.uprr.app.tng.spring.validation.HitPoints;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by david on 1/22/17.
 */
@Data
@NoArgsConstructor
public class PokemonUpdateRequest {
    @NotNull(message = "{validation.id.null}")
    private Integer id;
    @HitPoints
    private Integer hp;
    @Attack
    private Integer attack;
}
