package com.uprr.app.tng.spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by david on 8/8/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    private Integer id;
    private Integer hp;
    private Integer attack;

    public Pokemon(final Integer hp, final Integer attack) {
        this.hp = hp;
        this.attack = attack;
    }
}
