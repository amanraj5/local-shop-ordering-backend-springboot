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
    @Autowired
    private EmailService emailService;

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

        Order savedOrder = orderRepository.save(order);

        String itemList = savedOrder.getItems().stream()
                .map(i -> i.getName() + " x" + i.getQuantity())
                .collect(Collectors.joining(", "));

        String body = "Hi User,\n\n" +
                "Thanks for placing your order!\n" +
                "Order ID: " + savedOrder.getId() + "\n" +
                "Items: " + itemList + "\n" +
                "Total: ₹" + savedOrder.getTotalAmount() + "\n\n" +
                "We'll notify you when it's out for delivery.\n\n" +
                "Thank you for using LocalShop!";

        emailService.sendOrderConfirmation(savedOrder.getUserEmail(), "Order Confirmation", body);

        return savedOrder;
    }

    public List<Order> getOrder(String email){
        return orderRepository.findByUserEmail(email);
    }
}
