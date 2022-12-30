package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.services.RoleServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
@Component
public class RoleSeeder extends ISeeder<String> {

    private final RoleServices roleServices;

    @Override
    protected Collection<String> data() {
        return Arrays.asList("ROLE_ADMIN", "ROLE_SUPERVISOR", "ROLE_SUPPORT");
    }

    @Override
    public void seed() {
        for (String roleName : data()) {
            roleServices.firstOrCreate(roleName);
        }
    }
}
