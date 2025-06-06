package com.example.local_shop_ordering.controller;

import com.example.local_shop_ordering.dto.ProductRequest;
import com.example.local_shop_ordering.model.Product;
import com.example.local_shop_ordering.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
