package com.example.medibridge.dto.Prescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionItemDTO {
    private Integer id; // Optional for create
    private String medName;
    private String dosageForm;
    private int quantity;
    private int morning;
    private int noon;
    private int night;
}
