package com.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Models.Order;
import com.example.Models.PaymentDetails;
import com.example.Repositories.OrderRepository;
import com.example.Repositories.PaymentRepository;

import java.util.Optional;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private OrderRepository orderRepository;


	@Override
	public Optional<PaymentDetails> getPaymentStatus(Integer orderID) {
		// TODO Auto-generated method stub
		return paymentRepository.findByOrder_Id(orderID);
	}

	@Override
	public PaymentDetails processPayment(PaymentDetails payment) {
	    
	    if (payment.getOrder() == null) {
	        throw new RuntimeException("Order is not set in the PaymentDetails object.");
	    }


	    
	    Optional<Order> orderOptional = orderRepository.findById(payment.getOrder().getId());

	    if (orderOptional.isPresent()) {
	        
	        payment.setOrder(orderOptional.get());
	        return paymentRepository.save(payment);
	    } else {
	        
	        throw new RuntimeException("Order not found with ID: " + payment.getOrder().getId());
	    }
	}


}

