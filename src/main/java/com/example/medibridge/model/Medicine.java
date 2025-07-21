package com.example.medibridge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
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
    @JsonIgnore
    private Set<Store> stores;
}
