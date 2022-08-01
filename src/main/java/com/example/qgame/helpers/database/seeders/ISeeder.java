package com.example.qgame.helpers.database.seeders;

import com.example.qgame.QGameApplication;
import com.example.qgame.repositories.CategoryRepository;
import com.github.javafaker.Faker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public abstract class ISeeder<C> {

    protected abstract Collection<C> data();

    public abstract void seed();

    protected Faker faker(){
        return QGameApplication.getContext().getBean(Faker.class);
    }
}
