package com.example.payment.dto;

import jakarta.validation.constraints.*;

public class PaymentRequest {
    @NotNull @Min(1)
    private Double amount;
    @NotBlank
    private String currency;
    @NotBlank
    private String paymentMethod;
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
    
}