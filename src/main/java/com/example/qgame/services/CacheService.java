package com.example.qgame.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final CacheManager cacheManager;

    public void removeByKey(String... keys) {
        for (String key : keys) {
            Cache cache = cacheManager.getCache(key);
            if (cache != null) {
                cache.clear();
            }
        }

    }
}
