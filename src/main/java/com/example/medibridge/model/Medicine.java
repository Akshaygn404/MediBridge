package com.example.medibridge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
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

    @ManyToMany
    @JoinTable(
            name = "medicine_alternates",
            joinColumns = @JoinColumn(name = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "alternate_id")
    )
    private Set<Medicine> alternates = new HashSet<>();


}

