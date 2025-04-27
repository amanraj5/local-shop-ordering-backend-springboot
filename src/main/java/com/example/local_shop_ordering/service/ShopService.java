package com.example.local_shop_ordering.service;

import com.example.local_shop_ordering.model.Shops;
import com.example.local_shop_ordering.repo.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public List<Shops> getAllShops(){
        return shopRepository.findAll();
    }

    public List<Shops> addShop(List<Shops> shop){
        return shopRepository.saveAll(shop);
    }

    public Shops getShopById(String id) {
        Shops shops = shopRepository.findById(id).orElseThrow(() -> new RuntimeException("Shop not found with id: " + id));
        return shops;
    }
}
