package com.example.qgame.helpers.database.seeders;

import com.github.javafaker.Faker;

import java.util.Collection;

public abstract class ISeeder<C> {

    protected abstract Collection<C> data();

    public abstract void seed();

    protected Faker faker() {
        return new Faker();
    }
}
