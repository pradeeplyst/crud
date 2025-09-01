package com.example.mainapi.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching

	
	public class Cache {

	    @Bean
	    public CaffeineCacheManager cacheManager() {
	        CaffeineCacheManager cacheManager = new CaffeineCacheManager("weatherCache");
	        cacheManager.setAsyncCacheMode(true);
	        cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES));
	        return cacheManager;
	    }
	}

