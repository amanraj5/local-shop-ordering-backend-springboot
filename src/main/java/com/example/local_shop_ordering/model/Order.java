package com.example.local_shop_ordering.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.time.LocalDateTime;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private String userEmail;
    private Double totalAmount;
    private LocalDateTime timestamp;
    private List<OrderItem> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Order(String id, String userEmail, Double totalAmount, LocalDateTime timestamp, List<OrderItem> items) {
        this.id = id;
        this.userEmail = userEmail;
        this.totalAmount = totalAmount;
        this.timestamp = timestamp;
        this.items = items;
    }

    public Order() {
    }
}
