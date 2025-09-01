package com.example.mainapi.controller;

import com.example.mainapi.service.WeatherService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Value("${scheduler.cities:chennai,delhi,mumbai}")
    private String[] cities;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/all")
    public Map<String, Object> getAllWeather() {
        List<CompletableFuture<Map<String, Object>>> futures = Arrays.stream(cities)
                .map(weatherService::getWeatherAsync)
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        Map<String, Object> response = new HashMap<>();
        for (int i = 0; i < cities.length; i++) {
            try {
                response.put(cities[i], futures.get(i).join());
            } catch (CompletionException e) {
                response.put(cities[i], Map.of(
                        "error", "Failed to fetch weather",
                        "message", e.getCause().getMessage()
                ));
            }
        }

        return response;
    }

    @GetMapping("/{city}")
    public Map<String, Object> getWeatherByCity(@PathVariable String city) {
        try {
            return weatherService.getWeatherAsync(city.toLowerCase()).join();
        } catch (Exception e) {
            return Map.of(
                    "error", "Failed to get weather",
                    "message", e.getCause() != null ? e.getCause().getMessage() : e.getMessage()
            );
        }
    }

    @GetMapping("/filter")
    public Map<String, Object> getWeatherByCondition(@RequestParam String condition) {
        List<CompletableFuture<Map<String, Object>>> futures = Arrays.stream(cities)
                .map(weatherService::getWeatherAsync)
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        Map<String, Object> filteredCities = new HashMap<>();
        for (int i = 0; i < cities.length; i++) {
            try {
                Map<String, Object> weatherData = futures.get(i).join();

                String currentCondition = ((String) weatherData.getOrDefault("condition", "")).toLowerCase();

                if (currentCondition.contains(condition.toLowerCase())) {
                    filteredCities.put(cities[i], weatherData);
                }

            } catch (CompletionException e) {
                filteredCities.put(cities[i], Map.of(
                        "error", "Failed to fetch weather",
                        "message", e.getCause().getMessage()
                ));
            }
        }

        if (filteredCities.isEmpty()) {
            return Map.of("message", "No cities found with weather condition: " + condition);
        }

        return filteredCities;
    }
}
