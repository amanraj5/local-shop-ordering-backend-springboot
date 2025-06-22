package com.example.local_shop_ordering.repo;
import com.example.local_shop_ordering.model.Shops;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopRepository extends MongoRepository<Shops, String> {
}
