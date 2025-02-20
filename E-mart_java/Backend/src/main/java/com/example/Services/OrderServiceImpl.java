package com.example.Services;

import com.example.Models.*;
import com.example.Repositories.CartRepository;
import com.example.Repositories.OrderRepository;
import com.example.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Order placeOrder(int userId, int cartId, double amount, String paymentMethod) {
        
//        System.out.println("Placing order for userId: " + userId + ", cartId: " + cartId);

        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cartId));

        
        Order order = new Order(user, amount);
        order.setCart(cart);
        order.setOrderDate(LocalDateTime.now());

        try {
            order.setPaymentMethod(PaymentMethod.valueOf(paymentMethod.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid payment method: " + paymentMethod);
        }

        order.setStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }


    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrderStatus(int orderId, String status) {
        Order order = getOrderById(orderId);
        try {
            order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status);
        }
        return orderRepository.save(order);
    }
}
