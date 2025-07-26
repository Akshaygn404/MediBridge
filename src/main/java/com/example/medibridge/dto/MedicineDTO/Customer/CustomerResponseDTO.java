package com.example.medibridge.dto.MedicineDTO.Customer;

import com.example.medibridge.model.Store;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponseDTO{
    private int id;
    private String brandName;
    private String genericName;
    private String dosageForm;
}
