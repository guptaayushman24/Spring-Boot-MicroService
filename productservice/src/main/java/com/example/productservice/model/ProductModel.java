package com.example.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import javax.lang.model.type.IntersectionType;

@Entity
@Data
public class ProductModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double price;
    private Integer stock;
}
