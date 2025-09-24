package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableJpaRepositories
public class LmsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmsBackendApplication.class, args);
    }
}