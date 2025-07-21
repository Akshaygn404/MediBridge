package com.example.medibridge.dto.MedicineDTO;

import lombok.Data;

@Data
public class MedicineResponseDTO {
    private int id;
    private String brandName;
    private String genericName;
    private double price;
    private int stockQuantity;
}
