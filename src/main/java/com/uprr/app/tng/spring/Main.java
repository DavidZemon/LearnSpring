package com.uprr.app.tng.spring;

import com.uprr.app.tng.spring.hmi.HumanInterface;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by david on 8/8/16.
 */
public class Main {
    public static void main(final String... args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml")) {
            context.getBean(HumanInterface.class).run();
        }
    }
}
