package com.allianz.commercespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CommerceSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommerceSpringBootApplication.class, args);
    }

}
