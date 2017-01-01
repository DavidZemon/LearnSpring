package com.uprr.app.tng.spring.config;

import com.uprr.app.tng.spring.controller.HeartbeatController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by david on 1/8/17.
 */
@Configuration
public class ControllerConfig {
    @Bean
    public HeartbeatController heartBeatController() {
        return new HeartbeatController();
    }
}
