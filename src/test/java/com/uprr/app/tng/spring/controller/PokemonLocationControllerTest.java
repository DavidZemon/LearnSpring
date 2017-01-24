package com.uprr.app.tng.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uprr.app.tng.spring.controller.advice.GlobalExceptionHandler;
import com.uprr.app.tng.spring.dao.PokemonLocationDao;
import com.uprr.app.tng.spring.pojo.PokemonLocation;
import com.uprr.app.tng.spring.pojo.PokemonLocationCreateRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
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
 * Created by david on 1/23/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class PokemonLocationControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Mock private PokemonLocationDao pokemonLocationDao;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(new PokemonLocationController(this.pokemonLocationDao))
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    @Test
    public void test_get() throws Exception {
        final int expectedX = 1;
        final int expectedY = 2;
        final int actualId  = 3;
        when(this.pokemonLocationDao.getId(eq(expectedX), eq(expectedY))).thenReturn(actualId);

        this.mockMvc.perform(get("/pokemon-location/get/")
                                 .param("x", String.valueOf(expectedX))
                                 .param("y", String.valueOf(expectedY))
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.OK.value()))
                    .andExpect(jsonPath("$.pokemonId", is(actualId)));
    }

    @Test
    public void test_get_invalidLocation_expectError() throws Exception {
        final int expectedX = 1;
        final int expectedY = 2;
        when(this.pokemonLocationDao.getId(eq(expectedX), eq(expectedY)))
            .thenThrow(new EmptyResultDataAccessException(1));

        this.mockMvc.perform(get("/pokemon-location/get/")
                                 .param("x", String.valueOf(expectedX))
                                 .param("y", String.valueOf(expectedY))
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.title", is("Bad query")))
                    .andExpect(jsonPath("message", is("No data could be found at those coordinates")));
    }

    @Test
    public void test_create() throws Exception {
        final PokemonLocationCreateRequest request = new PokemonLocationCreateRequest();
        request.setX(1);
        request.setY(2);
        request.setPokemonId(3);

        this.mockMvc.perform(post("/pokemon-location/create")
                                 .content(OBJECT_MAPPER.writeValueAsString(request))
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.OK.value()));

        verify(this.pokemonLocationDao, times(1)).create(eq(new PokemonLocation(1, 2, 3)));
    }

    @Test
    public void test_create_missingX_expectError() throws Exception {
        final PokemonLocationCreateRequest request = new PokemonLocationCreateRequest();
        request.setY(2);
        request.setPokemonId(3);

        this.mockMvc.perform(post("/pokemon-location/create")
                                 .content(OBJECT_MAPPER.writeValueAsString(request))
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.title", is("Bad request")))
                    .andExpect(jsonPath("$.message", is("X coordinate cannot be null")));
    }

    @Test
    public void test_create_missingY_expectError() throws Exception {
        final PokemonLocationCreateRequest request = new PokemonLocationCreateRequest();
        request.setX(1);
        request.setPokemonId(3);

        this.mockMvc.perform(post("/pokemon-location/create")
                                 .content(OBJECT_MAPPER.writeValueAsString(request))
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.title", is("Bad request")))
                    .andExpect(jsonPath("$.message", is("Y coordinate cannot be null")));
    }

    @Test
    public void test_create_missingPokemonId_expectError() throws Exception {
        final PokemonLocationCreateRequest request = new PokemonLocationCreateRequest();
        request.setX(1);
        request.setY(2);

        this.mockMvc.perform(post("/pokemon-location/create")
                                 .content(OBJECT_MAPPER.writeValueAsString(request))
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.title", is("Bad request")))
                    .andExpect(jsonPath("$.message", is("Pokemon ID cannot be null")));
    }
}
