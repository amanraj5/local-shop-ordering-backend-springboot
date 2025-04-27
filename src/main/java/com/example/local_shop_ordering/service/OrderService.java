package com.example.local_shop_ordering.service;

import com.example.local_shop_ordering.dto.OrderRequest;
import com.example.local_shop_ordering.model.Order;
import com.example.local_shop_ordering.model.OrderItem;
import com.example.local_shop_ordering.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(OrderRequest request){
        Order order = new Order();

        order.setUserEmail(request.getUserId());
        order.setTotalAmount(request.getTotalAmount());

        List<OrderItem> orderItems = request.getItems().stream().map(itemDTO -> {
            OrderItem item = new OrderItem();
            item.setProductId(itemDTO.getProductId());
            item.setName(itemDTO.getName());
            item.setPrice(itemDTO.getPrice());
            item.setQuantity(itemDTO.getQuantity());
            return item;
        }).collect(Collectors.toList());

        order.setItems(orderItems);
        order.setTimestamp(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public List<Order> getOrder(String email){
        return orderRepository.findByUserEmail(email);
    }
}
