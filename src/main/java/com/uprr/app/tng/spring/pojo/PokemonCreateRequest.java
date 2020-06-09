package com.uprr.app.tng.spring.pojo;

import com.uprr.app.tng.spring.validation.Attack;
import com.uprr.app.tng.spring.validation.HitPoints;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by david on 1/22/17.
 */
@Data
@NoArgsConstructor
public class PokemonCreateRequest {
    @HitPoints
    private Integer hp;
    @Attack
    private Integer attack;
}
