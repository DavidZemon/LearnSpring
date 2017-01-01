package com.uprr.app.tng.spring.controller;

import com.uprr.app.tng.spring.pojo.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by david on 1/8/17.
 */
@RestController
public class HeartbeatController {
    public static final String DEFAULT_MESSAGE = "Hello, world!";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message echo() {
        return new Message(DEFAULT_MESSAGE);
    }
}
