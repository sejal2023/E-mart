package com.example.Controllers;

import com.example.Dtos.OrderDto;
//import com.example.Dtos.OrderRequest;
import com.example.Models.Order;
import com.example.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(@RequestBody OrderDto orderRequest) {
        return orderService.placeOrder(
                orderRequest.getUserId(),
                orderRequest.getCartId(),
                orderRequest.getAmount(),
                orderRequest.getPaymentMethod()
        );
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{orderId}/status")
    public Order updateOrderStatus(@PathVariable int orderId, @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }
}





//class OrderRequest {
//    private int userId;
//    private int cartId;
//    private double amount;
//    private String paymentMethod;
//
//    // Getters and Setters
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public int getCartId() {
//        return cartId;
//    }
//
//    public void setCartId(int cartId) {
//        this.cartId = cartId;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public String getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(String paymentMethod) {
//        this.paymentMethod = paymentMethod;
//    }
//}
