package com.example.medibridge.dto.Prescription;

import lombok.Data;

@Data
public class PrescriptionRequestDTO {
    int id;
    String medName;
    String dosageForm;
    int quantity;
    int morning=0;
    int noon=0;
    int night=0;
}
