package com.uprr.app.tng.spring.controller;

import com.uprr.app.tng.spring.exception.MyCustomException;
import com.uprr.app.tng.spring.pojo.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by david on 1/8/17.
 */
@RestController
@RequestMapping("heartbeat")
public class HeartbeatController {
    public static final String DEFAULT_MESSAGE = "Hello, world!";
    public static final String ERROR_MESSAGE   = "Oh no!";

    @ApiOperation("Simple check to ensure the server is up and running")
    @RequestMapping(path = "get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message echo() {
        return new Message(DEFAULT_MESSAGE);
    }

    @ApiOperation("It's important to test Java's internal algorithms at all stages of your program")
    @RequestMapping(path = "reverse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message reverse(@RequestParam
                           final String message) {
        return new Message(new StringBuilder(message).reverse().toString());
    }

    @ApiOperation("Converting a string to uppercase is hard. Let's make sure Oracle got it right.")
    @RequestMapping(path = "upper", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message toUpper(@RequestBody final Message message) {
        return new Message(message.getMessage().toUpperCase());
    }

    @ApiOperation("Try out a simple error scenario")
    @RequestMapping(path = "error", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void throwError () {
        throw new MyCustomException(ERROR_MESSAGE);
    }
}
