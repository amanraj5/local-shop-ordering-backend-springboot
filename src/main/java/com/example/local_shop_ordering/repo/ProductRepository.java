package com.example.local_shop_ordering.repo;

import com.example.local_shop_ordering.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findByShopId(String shopId);
}
