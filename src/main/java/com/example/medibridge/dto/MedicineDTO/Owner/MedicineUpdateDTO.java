package com.example.medibridge.dto.MedicineDTO.Owner;

import lombok.Data;

@Data
public class MedicineUpdateDTO {
    private String brandName;
    private String genericName;
    private String dosageForm;
    private Double price;
    private Integer stockQuantity;
}
