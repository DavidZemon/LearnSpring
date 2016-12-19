package com.uprr.app.tng.spring.dao;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import com.uprr.app.tng.spring.config.DaoConfig;
import com.uprr.app.tng.spring.config.PropertyConfig;
import com.uprr.app.tng.spring.pojo.Pokemon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.sql.DataSource;
import java.util.Arrays;

import static com.ninja_squad.dbsetup.Operations.insertInto;
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
    private static final Operation DELETE_ALL_POKEMON = Operations.deleteAllFrom("POKEMON_LOCATION", "POKEMON");

    @Autowired private DataSource                 dataSource;
    @Autowired private PlatformTransactionManager transactionManager;
    @Autowired private PokemonDao                 testable;

    private Destination destination;

    @Before
    public void setUp() throws Exception {
        this.destination = new TransactionAwareDestination(this.dataSource, this.transactionManager);
    }

    @Test
    public void get() throws Exception {
        this.dbSetup(DELETE_ALL_POKEMON, this.buildInsert(1, 2, 3));

        final Pokemon actual = this.testable.get(1);

        assertThat(actual.getId()).isEqualTo(1);
        assertThat(actual.getHp()).isEqualTo(2);
        assertThat(actual.getAttack()).isEqualTo(3);
    }

    @Test
    public void update() throws Exception {
        final int newHp     = 1000;
        final int newAttack = 9001;

        this.dbSetup(DELETE_ALL_POKEMON, this.buildInsert(1, 2, 3));

        final Pokemon expected = new Pokemon(1, newHp, newAttack);
        this.testable.update(expected);

        final Pokemon result = this.testable.get(1);

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getHp()).isEqualTo(newHp);
        assertThat(result.getAttack()).isEqualTo(newAttack);
    }

    @Test
    public void create() throws Exception {
        this.dbSetup(DELETE_ALL_POKEMON);

        final Pokemon expected = new Pokemon(5, 6);
        final int     id       = this.testable.create(expected);
        assertThat(expected.getId()).isEqualTo(id);

        final Pokemon actual = this.testable.get(id);
        assertThat(actual).isEqualTo(expected);
    }

    @Test(expected = IncorrectResultSizeDataAccessException.class)
    public void delete() throws Exception {
        this.dbSetup(DELETE_ALL_POKEMON, this.buildInsert(4, 5, 6));

        this.testable.delete(4);

        this.testable.get(4);
    }

    @Nonnull
    private Insert buildInsert(final int id, final int hp, final int attack) {
        return insertInto("POKEMON")
            .row()
            .column("ID", id)
            .column("HP", hp)
            .column("ATTACK", attack)
            .end()
            .build();
    }

    private void dbSetup(@Nonnull final Operation... operations) {
        final DbSetup dbSetup = new DbSetup(this.destination, Operations.sequenceOf(Arrays.asList(operations)));
        dbSetup.launch();
    }
}
