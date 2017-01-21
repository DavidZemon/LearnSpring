package com.uprr.app.tng.spring.controller;

import com.uprr.app.tng.spring.controller.advice.GlobalExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by david on 1/8/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class HeartbeatControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(new HeartbeatController())
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    @Test
    public void echo() throws Exception {
        this.mockMvc.perform(get("/heartbeat/get")
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.OK.value()))
                    .andExpect(jsonPath("$.message", is(HeartbeatController.DEFAULT_MESSAGE)));
    }

    @Test
    public void reverse() throws Exception {
        this.mockMvc.perform(post("/heartbeat/reverse")
                                 .param("message", "foobar")
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.OK.value()))
                    .andExpect(jsonPath("$.message", is("raboof")));
    }

    @Test
    public void upper() throws Exception {
        //language=JSON
        final String message = "{\n" +
            "    \"message\": \"fooBar123\"\n" +
            "}";
        this.mockMvc.perform(post("/heartbeat/upper")
                                 .content(message)
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.OK.value()))
                    .andExpect(jsonPath("$.message", is("FOOBAR123")));
    }

    @Test
    public void test_myCustomExceptionThrown_appropriateResponseReceived() throws Exception {
        this.mockMvc.perform(get("/heartbeat/error")
                                 .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                    .andExpect(jsonPath("$.title", is("Custom error")))
                    .andExpect(jsonPath("$.message", is(HeartbeatController.ERROR_MESSAGE)));
    }
}
