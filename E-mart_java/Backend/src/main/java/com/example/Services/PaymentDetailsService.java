package com.example.Services;



import java.util.Optional;

import com.example.Models.PaymentDetails;

public interface PaymentDetailsService {
    PaymentDetails processPayment(PaymentDetails payment);
    
    Optional<PaymentDetails> getPaymentStatus(Integer orderID);
}

