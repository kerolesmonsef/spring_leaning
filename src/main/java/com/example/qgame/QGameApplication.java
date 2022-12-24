package com.example.qgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing // for now for timestamp use current
public class QGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(QGameApplication.class, args);
    }

}
