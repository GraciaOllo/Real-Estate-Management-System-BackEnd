package com.example.demo.service;

import com.example.demo.model.Payment;

import java.math.BigDecimal;

public class PaymentService {
    public Payment createMobilePayment(Long bookingId, String mobileNumber, String providerName, BigDecimal amount) {
        return null;
    }

    public Payment createCashPayment(Long bookingId, String receiptNumber, BigDecimal amount) {
        return null;
    }
}
