package com.uprr.app.tng.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uprr.app.tng.spring.dao.PokemonDao;
import com.uprr.app.tng.spring.pojo.Pokemon;
import com.uprr.app.tng.spring.pojo.PokemonCreateRequest;
import com.uprr.app.tng.spring.pojo.PokemonUpdateRequest;
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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by david on 1/15/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class PokemonControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

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

    @Test
    public void test_update() throws Exception {
        final PokemonUpdateRequest request = new PokemonUpdateRequest();
        request.setId(1);
        request.setHp(2);
        request.setAttack(3);

        this.mockMvc.perform(post("/pokemon/update")
                                 .content(OBJECT_MAPPER.writeValueAsString(request))
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.OK.value()));

        verify(this.pokemonDao, times(1)).update(eq(new Pokemon(1, 2, 3)));
    }

    @Test
    public void test_delete() throws Exception {
        final int expectedId = 1;
        this.mockMvc.perform(post("/pokemon/delete/" + expectedId)
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.OK.value()));

        verify(this.pokemonDao, times(1)).delete(expectedId);
    }

    @Test
    public void test_create() throws Exception {
        final PokemonCreateRequest request = new PokemonCreateRequest();
        request.setHp(2);
        request.setAttack(3);

        final Pokemon expectedPokemon = new Pokemon(2, 3);
        when(this.pokemonDao.create(eq(expectedPokemon))).thenReturn(1);

        this.mockMvc.perform(post("/pokemon/create")
                                 .content(OBJECT_MAPPER.writeValueAsString(request))
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.OK.value()))
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.hp", is(2)))
                    .andExpect(jsonPath("$.attack", is(3)));
    }
}
