package com.example.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ProductModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String price;
    private String stock;
}
