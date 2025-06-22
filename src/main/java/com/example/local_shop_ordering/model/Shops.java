package com.example.local_shop_ordering.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shops")
public class Shops {
    @Id
    private String id;
    private String name;
    private double rating;
    private String description;
    private String image;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Shops(String id, String name, double rating, String description, String image) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.image = image;
    }

    public Shops() {
    }
}
