package com.example.local_shop_ordering.controller;

import com.example.local_shop_ordering.model.Shops;
import com.example.local_shop_ordering.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shops")
    public List<Shops> getAllShops(){
        return shopService.getAllShops();
    }

//    @PostMapping("/addShop")
//    public List<Shops> addShop(@RequestBody List<Shops> shop) {
//        return shopService.addShop(shop);
//    }

    @PostMapping("/addShop")
    public ResponseEntity<String> addShop(
            @RequestParam("name") String name,
            @RequestParam("rating") double rating,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            String imageData = Base64.getEncoder().encodeToString(imageFile.getBytes());

            Shops newShop = new Shops();
            newShop.setName(name);
            newShop.setRating(rating);
            newShop.setDescription(description);
            newShop.setImage(imageData);

            shopService.saveShop(newShop);

            return ResponseEntity.status(HttpStatus.CREATED).body("Shop added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/shops/{id}")
    public Shops getShopById(@PathVariable String id){
        return shopService.getShopById(id);
    }


    @DeleteMapping("/shops/{id}")
    public String deleteShop(@PathVariable String id){
        shopService.deleteShop(id);
        return "Shop deleted Successfully";
    }
}
