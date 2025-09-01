package com.example.Mockapi.Controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class MockWeatherController {

    @GetMapping("/{city}")
    public Map<String, Object> getWeather(@PathVariable String city) {
        Map<String, Object> weather = new HashMap<>();

        int temp = 20 + (int) (Math.random() * 15); // Random temp 20–35
        int humidity = 40 + (int) (Math.random() * 60); // Random humidity 40–100

        List<String> conditions = Arrays.asList("Sunny", "Cloudy", "Rainy", "Windy", "Stormy");

        String randomCondition = conditions.get((int) (Math.random() * conditions.size()));

        weather.put("city", city);
        weather.put("temperature", temp + "°C");
        weather.put("humidity", humidity + "%");
        weather.put("condition", randomCondition);
        weather.put("time", LocalDateTime.now().toString());

        return weather;
    }
}
