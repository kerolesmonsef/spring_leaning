package com.example.qgame;

import com.example.qgame.helpers.database.seeders.impls.BlogSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@SpringBootApplication
@EnableJpaAuditing // for now for timestamp use current
public class QGameApplication implements CommandLineRunner {

    private static ConfigurableApplicationContext context;

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {

        QGameApplication.context = SpringApplication.run(QGameApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        new BlogSeeder().seed();
//        System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString());
//        System.out.println("aaaa");
    }
}
