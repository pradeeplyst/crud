package com.example.Mockapi;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MockapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockapiApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	    return builder
	        .setConnectTimeout(Duration.ofSeconds(5))
	        .setReadTimeout(Duration.ofSeconds(10))
	        .build();
	}


}
