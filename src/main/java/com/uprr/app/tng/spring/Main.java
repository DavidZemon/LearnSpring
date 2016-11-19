package com.uprr.app.tng.spring;

import com.uprr.app.tng.spring.config.MainConfig;
import com.uprr.app.tng.spring.hmi.HumanInterface;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by david on 8/8/16.
 */
public class Main {
    public static void main(final String... args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class)) {
            context.getBean(HumanInterface.class).run();
        }
    }
}
