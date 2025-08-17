package com.example.medibridge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String medName;
    private String dosageForm;
    private int quantity;
    private int morning;
    private int noon;
    private int night;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
}
