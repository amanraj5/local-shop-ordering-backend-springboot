package com.example.local_shop_ordering.controller;

import com.example.local_shop_ordering.dto.OrderRequest;
import com.example.local_shop_ordering.model.Order;
import com.example.local_shop_ordering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeorder")
    public Order placeOrder(@RequestBody OrderRequest orderRequest) {
        if (orderRequest.getUserId() == null || orderRequest.getUserId().isEmpty()) {
            throw new RuntimeException("User must be logged in to place an order.");
        }
        return orderService.placeOrder(orderRequest);
    }

    @GetMapping("/getorder/{email}")
    public List<Order> getOrder(@PathVariable String email){
        return orderService.getOrder(email);
    }
}
