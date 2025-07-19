package com.example.medibridge.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String storeName;
    private String location;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "store_medicine",
            joinColumns = @JoinColumn(name="store_id"),
            inverseJoinColumns = @JoinColumn(name="medicine_id")
    )
    private List<Medicine> medicines;

}
