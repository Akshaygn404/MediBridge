package com.example.medibridge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String medName;
    private String dosageForm;
    private int quantity;
    private int morning;
    private int noon;
    private int night;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonBackReference
    private Customer customer;
}
