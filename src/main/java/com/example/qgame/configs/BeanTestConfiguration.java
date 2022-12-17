package com.example.qgame.configs;

import com.example.qgame.services.test.TestProductService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class BeanTestConfiguration {

    @Bean
    @Primary
    public TestProductService testProductServiceBean() {
        return Mockito.mock(TestProductService.class);
    }
}
