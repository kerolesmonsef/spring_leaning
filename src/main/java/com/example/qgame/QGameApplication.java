package com.example.qgame;

import com.example.qgame.Models.Product;
import com.example.qgame.Models.ProductLike;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.database.seeders.impls.BlogSeeder;
import com.example.qgame.helpers.ids.ProductLikeId;
import com.example.qgame.repositories.ProductLikeRepository;
import com.example.qgame.repositories.ProductRepository;
import com.example.qgame.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@SpringBootApplication
@EnableJpaAuditing // for now for timestamp use current
public class QGameApplication {

    private static ConfigurableApplicationContext context;

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {
        context = SpringApplication.run(QGameApplication.class, args);
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }


}
