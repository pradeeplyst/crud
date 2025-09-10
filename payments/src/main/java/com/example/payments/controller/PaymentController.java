package com.example.payments.controller;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.entity.Payment;
import com.example.payments.service.PaymentService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;  

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/process")
    public ResponseEntity<PaymentResponse> process(@Valid @RequestBody PaymentRequest req) {
        Payment p = service.process(req);  
        service.sendReceipt(p.getId());

        if ("COMPLETED".equals(p.getStatus())) {
            return ResponseEntity.ok(new PaymentResponse("Payment successful", p.getId()));
        } else {
            return ResponseEntity.status(503)
                    .body(new PaymentResponse("Payment service unavailable", p.getId()));
        }
    }

    @GetMapping("/process/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        Optional<Payment> paymentOpt = service.findById(id);

        return paymentOpt.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
