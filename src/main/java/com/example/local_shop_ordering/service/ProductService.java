package com.example.local_shop_ordering.service;

import com.example.local_shop_ordering.dto.ProductRequest;
import com.example.local_shop_ordering.model.Product;
import com.example.local_shop_ordering.model.Shops;
import com.example.local_shop_ordering.repo.ProductRepository;
import com.example.local_shop_ordering.repo.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;

    public List<Product> addProductByShopId(@RequestBody List<ProductRequest> requests){

        List<Product> productsToSave = new ArrayList<>();

        for (ProductRequest request : requests) {
            // Validate shopId
            Shops shop = shopRepository.findById(request.getShopId())
                    .orElseThrow(() -> new RuntimeException("Shop not found with id: " + request.getShopId()));

            Product product = new Product();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setImageUrl(request.getImageUrl());
            product.setPrice(request.getPrice());
            product.setShopId(request.getShopId());

            productsToSave.add(product);
        }

        return productRepository.saveAll(productsToSave);
    }
    public Optional<List<Product>> getProductByShopId(String shopId){
        return productRepository.findByShopId(shopId);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
