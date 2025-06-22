package com.example.local_shop_ordering.repo;

import com.example.local_shop_ordering.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserEmail(String email);
}
