package com.example.mainapi.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class WeatherService {

	@Async("taskExecutor")
	public CompletableFuture<Map<String, Object>> getWeatherAsync(String city) {
	    return CompletableFuture.completedFuture(getWeather(city));
	}

	@Cacheable(value = "weatherCache", key = "#city", unless = "#result == null || #result.containsKey('error')")
	public Map<String, Object> getWeather(String city) {
	    try {
	        Thread.sleep(2000); 

	        if (Math.random() > 0.7) {
	            throw new RuntimeException("Mock API failed for city: " + city);
	        }

	        int tempCelsius = 20 + new Random().nextInt(10); 
	        int humidity = 40 + new Random().nextInt(30);    

	        Map<String, Object> weather = new HashMap<>();
	        weather.put("city", city);
	        weather.put("temperature", tempCelsius + " °C");
	        weather.put("humidity", humidity + " %");
	        weather.put("condition", Arrays.asList("Sunny", "Rainy", "Cloudy")
	                .get(new Random().nextInt(3)));
	        weather.put("time", new Date().toString());

	        System.out.println("Fetched weather data for: " + city);
	        return weather;

	    } catch (Exception e) {
	        System.err.println("Error fetching weather for " + city + ": " + e.getMessage());

	        Map<String, Object> fallback = new HashMap<>();
	        fallback.put("city", city);
	        fallback.put("error", "Unable to fetch weather data at this time.");
	        fallback.put("time", new Date().toString());
	        return fallback;
	    }
	}

	}

