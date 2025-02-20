package com.example.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Models.Cart;
import com.example.Models.Order;
import com.example.Models.PaymentDetails;
import com.example.Services.OrderService;
import com.example.Services.PaymentDetailsService;

import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    @Autowired
    private PaymentDetailsService paymentService;
    @Autowired
    private OrderService orderService;
    
    
    @PostMapping("/process")
    public ResponseEntity<PaymentDetails> processPayment(@RequestBody PaymentDetails payment) {
    	PaymentDetails savedPayment = paymentService.processPayment(payment);
        return ResponseEntity.ok(savedPayment);
    }
    
   
    
    @GetMapping("/status/{orderID}")
    public ResponseEntity<?> getPaymentStatus(@PathVariable Integer orderID) {
        
        Optional<PaymentDetails> payment = paymentService.getPaymentStatus(orderID);

        return payment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

