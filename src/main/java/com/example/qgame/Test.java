package com.example.qgame;

import com.example.qgame.Models.Product;
import com.example.qgame.repositories.ProductRepository;
import com.example.qgame.testpackage.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Test implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("------------------------------------------------");
        System.out.println("dname :" + applicationContext.getDisplayName());
        System.out.println("id : " + applicationContext.getId());

        MyBean myBean = applicationContext.getBean(MyBean.class);
        System.out.println(" myBean id :" + myBean.getApplicationId());
    }
}
