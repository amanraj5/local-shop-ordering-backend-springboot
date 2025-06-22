package com.example.local_shop_ordering.controller;

import com.example.local_shop_ordering.dto.ProductRequest;
import com.example.local_shop_ordering.model.Product;
import com.example.local_shop_ordering.model.Shops;
import com.example.local_shop_ordering.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    @Autowired
    public ProductService productService;

    @PostMapping
    public ResponseEntity<List<Product>> addProductByShopId(@RequestBody List<ProductRequest> requests){
        List<Product> product = productService.addProductByShopId(requests);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(
            @RequestParam("shopId") String shopId,
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile
    ){
        try {
            String imageData = Base64.getEncoder().encodeToString(imageFile.getBytes());

            Product newProduct = new Product();
            newProduct.setShopId(shopId);
            newProduct.setName(name);
            newProduct.setPrice(price);
            newProduct.setDescription(description);
            newProduct.setImageUrl(imageData);

            productService.saveShop(newProduct);

            return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/shops/{shopId}")
    public ResponseEntity<Optional<List<Product>>> getProductByShopId(@PathVariable String shopId){
        return ResponseEntity.ok(productService.getProductByShopId(shopId));
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}
