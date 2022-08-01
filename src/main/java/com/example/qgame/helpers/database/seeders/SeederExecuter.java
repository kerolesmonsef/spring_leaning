package com.example.qgame.helpers.database.seeders;

import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class SeederExecuter {
    private List<ISeeder> iSeeders;

    public SeederExecuter(List<ISeeder> iSeeders) {
        this.iSeeders = iSeeders;
    }

    public SeederExecuter(ISeeder iSeeder) {
        this.iSeeders.add(iSeeder);
    }


    public void execute() {
        for (ISeeder seeder : this.iSeeders) {
            seeder.seed();
            log.info("---------------- done seeding : " + seeder.getClass());
        }
    }
}
