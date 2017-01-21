package com.uprr.app.tng.spring.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by david on 1/22/17.
 */
public class PokemonCreateRequest {
    private Integer hp;
    private Integer attack;

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

        final PokemonCreateRequest that = (PokemonCreateRequest) o;

        return new EqualsBuilder()
            .append(this.hp, that.hp)
            .append(this.attack, that.attack)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(this.hp)
            .append(this.attack)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("hp", this.hp)
            .append("attack", this.attack)
            .toString();
    }
}
