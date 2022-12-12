package com.example.qgame.testpackage;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyBean  implements ApplicationContextAware {

    private String applicationId;



    public String getApplicationId() {

        return applicationId;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationId = applicationContext.getId();
    }
}