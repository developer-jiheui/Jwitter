package com.jwt.jwitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:database.properties")
public class JwitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwitterApplication.class, args);
    }
}
