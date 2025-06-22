package com.example.local_shop_ordering.repo;

import com.example.local_shop_ordering.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,String> {
    Optional<List<Product>> findByShopId(String shopId);
}
