package com.example.local_shop_ordering.repo;

import com.example.local_shop_ordering.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin,String> {
    Optional<Admin> findByEmail(String userId);
}
