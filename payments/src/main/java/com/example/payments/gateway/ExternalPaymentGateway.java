package com.example.payments.gateway;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class ExternalPaymentGateway {
    private final Random random = new Random();

    public String charge(Double amount, String currency, String method) {
        if (random.nextInt(10) < 3) {
            throw new RuntimeException("Gateway timeout");
        }
        return "SUCCESS";
    }
}
