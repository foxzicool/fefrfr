package com.example.demo.controller;

import com.example.demo.model.UserOrder;
import com.example.demo.repository.UserOrderRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserOrderController {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @GetMapping("/user_orders")
    public List<UserOrder> getAllOrders() {
        return userOrderRepository.findAll();
    }

    @GetMapping("/user_orders/{id}")
    public UserOrder getOrderById(@PathVariable Long id) {
        return userOrderRepository.findById(id).orElse(null);
    }

    @PostMapping("/user_orders")
    public ResponseEntity<?> createOrder(@Valid @RequestBody UserOrder order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid order details");
        }

        System.out.println("Creating order with details: " + order.toString());
        try {
            return ResponseEntity.ok(userOrderRepository.save(order));
        } catch (Exception e) {
            System.err.println("Error while saving order: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error while saving order");
        }
    }

    @PutMapping("/user_orders/{id}")
    public UserOrder updateOrder(@PathVariable Long id, @RequestBody UserOrder orderDetails) {
        UserOrder order = userOrderRepository.findById(id).orElse(null);
        if (order == null)
            return null;

        order.setEmail(orderDetails.getEmail());
        order.setRecipientName(orderDetails.getRecipientName());
        order.setRecipientPhone(orderDetails.getRecipientPhone());
        order.setRecipientAddress(orderDetails.getRecipientAddress());
        order.setMessage(orderDetails.getMessage());

        return userOrderRepository.save(order);
    }

    @DeleteMapping("/user_orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        userOrderRepository.deleteById(id);
    }
}
