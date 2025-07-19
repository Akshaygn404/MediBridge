package com.example.medibridge.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brandName;
    private String genericName;
    private String activeIngredients;
    private String therapeuticClass;
    private String dosageForm;
    private String strength;
    private String manufacturer;
    private double price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "medicines")
    private Set<Store> stores;
}
