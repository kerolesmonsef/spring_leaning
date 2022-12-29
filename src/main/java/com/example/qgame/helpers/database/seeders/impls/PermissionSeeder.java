package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.services.PermissionService;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
public class PermissionSeeder extends ISeeder<String> {

    private final PermissionService permissionService;

    @Override
    protected Collection<String> data() {
        return Arrays.asList(
                "ADD_PRODUCT", "EDIT_PRODUCT", "SHOW_PRODUCT", "LIST_PRODUCT",
                "ADD_BLOG", "EDIT_BLOG", "SHOW_BLOG", "LIST_BLOG",
                "SEND_EMAIL"
        );
    }

    @Override
    public void seed() {
        for (String permissionName : data()){
            permissionService.firstOrCreate(permissionName);
        }


    }
}
