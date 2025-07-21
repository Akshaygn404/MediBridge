package com.example.medibridge.dto.MedicineDTO;

import lombok.Data;

@Data
public class MedicineRequestDTO {
    private String brandName;
    private String genericName;
    private String dosageForm;
    private double price;
    private int stockQuantity;
}

