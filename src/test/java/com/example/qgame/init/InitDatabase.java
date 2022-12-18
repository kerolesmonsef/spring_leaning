package com.example.qgame.init;

import com.example.qgame.controllers.SeederController;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@SpringBootTest
@ActiveProfiles("test")
public class InitDatabase {

    @Autowired
    private SeederController seederController;

//    @Test
//    public void seedDatabase(){
//        seederController.seed();
//    }
}
