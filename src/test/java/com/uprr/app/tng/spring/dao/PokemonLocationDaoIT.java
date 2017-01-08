package com.uprr.app.tng.spring.dao;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import com.uprr.app.tng.spring.config.DaoConfig;
import com.uprr.app.tng.spring.config.PropertyConfig;
import com.uprr.app.tng.spring.pojo.PokemonLocation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by david on 1/8/17.
 */
@SuppressWarnings("MagicNumber")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class, PropertyConfig.class})
@Transactional
@Rollback
public class PokemonLocationDaoIT {
    private static final Operation DELETE_ALL_POKEMON_LOCATIONS = Operations.deleteAllFrom("POKEMON_LOCATION",
                                                                                           "POKEMON");
    private static final Operation BUILD_DEFAULT_POKEMON        = insertInto("POKEMON").row()
                                                                                       .column("ID", 3)
                                                                                       .column("HP", 4)
                                                                                       .column("ATTACK", 5)
                                                                                       .end()
                                                                                       .build();

    @Autowired private DataSource                 dataSource;
    @Autowired private PlatformTransactionManager transactionManager;
    @Autowired private PokemonLocationDao         testable;

    private Destination destination;

    @Before
    public void setUp() throws Exception {
        this.destination = new TransactionAwareDestination(this.dataSource, this.transactionManager);
    }

    @Test
    public void getId() throws Exception {
        this.dbSetup(DELETE_ALL_POKEMON_LOCATIONS, BUILD_DEFAULT_POKEMON, this.buildInsert(1, 2, 3));

        assertThat(this.testable.getId(1, 2)).isEqualTo(3);
    }

    @Test
    public void create() throws Exception {
        this.dbSetup(DELETE_ALL_POKEMON_LOCATIONS, BUILD_DEFAULT_POKEMON);

        this.testable.create(new PokemonLocation(1, 2, 3));
        assertThat(this.testable.getId(1, 2)).isEqualTo(3);
    }

    @Nonnull
    private Insert buildInsert(final int x, final int y, final int pokemonId) {
        return insertInto("POKEMON_LOCATION")
            .row()
            .column("X", x)
            .column("Y", y)
            .column("POKEMON_ID", pokemonId)
            .end()
            .build();
    }

    private void dbSetup(@Nonnull final Operation... operations) {
        final DbSetup dbSetup = new DbSetup(this.destination, Operations.sequenceOf(Arrays.asList(operations)));
        dbSetup.launch();
    }
}
