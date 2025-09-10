package com.example.payments.service;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.entity.Payment;
import com.example.payments.gateway.ExternalPaymentGateway;
import com.example.payments.repository.PaymentRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    private final com.example.payments.gateway.ExternalPaymentGateway gateway;
    private final PaymentRepository repo;

    public PaymentService(ExternalPaymentGateway gateway, PaymentRepository repo) {
        this.gateway = gateway;
        this.repo = repo;
    }

    @CircuitBreaker(name = "paymentGateway", fallbackMethod = "fallback")
    public Payment process(PaymentRequest req) {
        gateway.charge(req.getAmount(), req.getCurrency(), req.getPaymentMethod());

        Payment p = new Payment();
        p.setAmount(req.getAmount());
        p.setCurrency(req.getCurrency());
        p.setPaymentMethod(req.getPaymentMethod()); 
        p.setStatus("SUCCESS");                   
        p.setCreatedAt(LocalDateTime.now());

        return repo.save(p);
    }

    public Payment fallback(PaymentRequest req, Throwable ex) {
        Payment p = new Payment();
        p.setAmount(req.getAmount());
        p.setCurrency(req.getCurrency());
        p.setPaymentMethod(req.getPaymentMethod()); 
        p.setStatus("FAILED_GATEWAY");              
        p.setCreatedAt(LocalDateTime.now());

        return repo.save(p);
    }

    @Async
    public void sendReceipt(Long id) {
        System.out.println("Receipt sent for payment " + id);
    }

	public Optional<Payment> findById1(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Optional<Payment> findById(Long id) {
	    return repo.findById(id);
	}

}
