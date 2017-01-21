package com.uprr.app.tng.spring.controller;

import com.uprr.app.tng.spring.exception.MyCustomException;
import com.uprr.app.tng.spring.pojo.Message;
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

    @RequestMapping(path = "get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message echo() {
        return new Message(DEFAULT_MESSAGE);
    }

    @RequestMapping(path = "reverse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message reverse(@RequestParam
                           final String message) {
        return new Message(new StringBuilder(message).reverse().toString());
    }

    @RequestMapping(path = "upper", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message toUpper(@RequestBody final Message message) {
        return new Message(message.getMessage().toUpperCase());
    }

    @RequestMapping(path = "error", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void throwError () {
        throw new MyCustomException(ERROR_MESSAGE);
    }
}
