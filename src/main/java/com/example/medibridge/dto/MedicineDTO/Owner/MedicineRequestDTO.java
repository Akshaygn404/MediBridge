package com.example.medibridge.dto.MedicineDTO.Owner;

import lombok.Data;

@Data
public class MedicineRequestDTO {
    private String brandName;
    private String genericName;
    private String dosageForm;
    private double price;
    private int stockQuantity;
}

