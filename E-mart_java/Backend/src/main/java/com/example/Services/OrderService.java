package com.example.Services;

import com.example.Models.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(int userId, int cartId, double amount, String paymentMethod);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
    Order updateOrderStatus(int orderId, String status);
}
