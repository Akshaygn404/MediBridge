package com.example.medibridge.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Prescription {
    private int id;
    private String medName;
    private String dosageForm;
    private int quantity;
    private int morning;
    private int noon;
    private int night;
}
