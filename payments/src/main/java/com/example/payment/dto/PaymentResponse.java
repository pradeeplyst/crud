package com.example.payment.dto;

public class PaymentResponse {
    private String message;
    private Long paymentId;
    public PaymentResponse(String msg, Long id) {
        this.message = msg; this.paymentId = id;
    }
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
   
}