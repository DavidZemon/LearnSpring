package com.uprr.app.tng.spring.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by david on 8/8/16.
 */
@SuppressWarnings("unused")
public class Pokemon {
    private Integer id;
    private Integer hp;
    private Integer attack;

    public Pokemon(final int hp, final int attack) {
        this.hp = hp;
        this.attack = attack;
    }

    public Pokemon(final int id, final int hp, final int attack) {
        this.id = id;
        this.hp = hp;
        this.attack = attack;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getHp() {
        return this.hp;
    }

    public void setHp(final Integer hp) {
        this.hp = hp;
    }

    public Integer getAttack() {
        return this.attack;
    }

    public void setAttack(final Integer attack) {
        this.attack = attack;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        final Pokemon pokemon = (Pokemon) o;

        return new EqualsBuilder()
            .append(this.id, pokemon.id)
            .append(this.hp, pokemon.hp)
            .append(this.attack, pokemon.attack)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(this.id)
            .append(this.hp)
            .append(this.attack)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", this.id)
            .append("hp", this.hp)
            .append("attack", this.attack)
            .toString();
    }
}
