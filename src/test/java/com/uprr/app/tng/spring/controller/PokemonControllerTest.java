package com.uprr.app.tng.spring.controller;

import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.pojo.Pokemon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by david on 1/15/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class PokemonControllerTest {
    @Mock private PokemonDao pokemonDao;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PokemonController(this.pokemonDao)).build();
    }

     @Test
    public void test_get() throws Exception {
        final int expectedId = 1;
        when(this.pokemonDao.get(expectedId)).thenReturn(new Pokemon(expectedId, 2, 3));

        this.mockMvc.perform(get("/pokemon/get/" + expectedId)
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.OK.value()))
                    .andExpect(jsonPath("$.id", is(expectedId)))
                    .andExpect(jsonPath("$.hp", is(2)))
                    .andExpect(jsonPath("$.attack", is(3)));
    }
}
