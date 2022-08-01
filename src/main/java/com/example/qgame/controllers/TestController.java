package com.example.qgame.controllers;

import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.helpers.database.seeders.SeederExecuter;
import com.example.qgame.helpers.database.seeders.impls.CategorySeeder;
import com.example.qgame.helpers.database.seeders.impls.OptionSeeder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

    @ResponseBody
    @GetMapping("/seed")
    public void seed() {

        List<ISeeder> seeders = Arrays.asList(new CategorySeeder(), new OptionSeeder());


        new SeederExecuter(seeders).execute();
    }
}
