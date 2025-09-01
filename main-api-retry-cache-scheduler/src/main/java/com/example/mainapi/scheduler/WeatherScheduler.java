package com.example.mainapi.scheduler;

import com.example.mainapi.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class WeatherScheduler {

    private static final Logger log = LoggerFactory.getLogger(WeatherScheduler.class);

    private final WeatherService weatherService;
    private final List<String> cities;

    public WeatherScheduler(WeatherService weatherService,
                            @Value("${scheduler.cities:chennai,delhi,mumbai}") String[] preload) {
        this.weatherService = weatherService;
        this.cities = Arrays.stream(preload)
                            .map(String::toLowerCase)
                            .collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshHourly() {
        log.info("Scheduled refresh for cities: {}", cities);

        cities.forEach(city -> {
            Map<String, Object> weather = weatherService.getWeather(city);
            log.info("Refreshed weather for {} -> {}", city, weather);
        });

        log.info("All cities refreshed successfully.");
    }

}
