package com.example.local_shop_ordering.controller;

import com.example.local_shop_ordering.model.Shops;
import com.example.local_shop_ordering.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/shops")
@CrossOrigin
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping
    public List<Shops> getAllShops(){
        return shopService.getAllShops();
    }

    @PostMapping
    public List<Shops> addShop(@RequestBody List<Shops> shop) {
        return shopService.addShop(shop);
    }
    
    @GetMapping("{id}")
    public Shops getShopById(@PathVariable String id){
        return shopService.getShopById(id);
    }
}
