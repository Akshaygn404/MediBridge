package com.example.medibridge.dto.MedicineDTO.Customer;

import lombok.Data;

@Data
public class CustomerRequestDTO {
    private String brandName;
    private String category;
    private String storeName;
    private String genericName;

}
