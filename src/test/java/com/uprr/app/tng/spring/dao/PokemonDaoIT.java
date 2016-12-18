package com.uprr.app.tng.spring.dao;

import com.uprr.app.tng.spring.config.DaoConfig;
import com.uprr.app.tng.spring.config.PropertyConfig;
import com.uprr.app.tng.spring.pojo.Pokemon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by david on 12/11/16.
 */
@SuppressWarnings("MagicNumber")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class, PropertyConfig.class})
@Transactional
@Rollback
public class PokemonDaoIT {
    @Autowired private PokemonDao testable;

    @Test
    public void get() throws Exception {
        final Pokemon actual = this.testable.get(1);

        assertThat(actual.getId()).isEqualTo(1);
        assertThat(actual.getHp()).isEqualTo(50);
        assertThat(actual.getAttack()).isEqualTo(10);
    }

    @Test
    public void update() throws Exception {
        final int newHp = 1000;
        final int newAttack = 9001;

        final Pokemon start = this.testable.get(1);

        start.setHp(newHp);
        start.setAttack(newAttack);

        this.testable.update(start);

        final Pokemon result = this.testable.get(1);

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getHp()).isEqualTo(newHp);
        assertThat(result.getAttack()).isEqualTo(newAttack);
    }

    @Test
    public void create() throws Exception {
        final Pokemon expected = new Pokemon(5, 6);
        final int     id       = this.testable.create(expected);
        assertThat(expected.getId()).isEqualTo(id);

        final Pokemon actual = this.testable.get(id);
        assertThat(actual).isEqualTo(expected);
    }

    @Test(expected = IncorrectResultSizeDataAccessException.class)
    public void delete() throws Exception {
        final Pokemon expected = new Pokemon(5, 6);
        final int     id       = this.testable.create(expected);

        this.testable.delete(id);

        this.testable.get(id);
    }
}
