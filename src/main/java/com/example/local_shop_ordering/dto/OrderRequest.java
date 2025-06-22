package com.example.local_shop_ordering.dto;

import com.example.local_shop_ordering.model.OrderItem;

import java.util.List;

public class OrderRequest {
    private String userId; //
    private String shopId;
    private double totalAmount;
    private List<OrderItem> items;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public OrderRequest(String userId, String shopId, double totalAmount, List<OrderItem> items) {
        this.userId = userId;
        this.shopId = shopId;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public OrderRequest() {
    }
}
