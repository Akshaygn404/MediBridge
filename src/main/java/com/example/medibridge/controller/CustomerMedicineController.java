package com.example.medibridge.controller;


import com.example.medibridge.dto.MedicineDTO.Customer.CustomerRequestDTO;
import com.example.medibridge.dto.MedicineDTO.Customer.CustomerResponseDTO;
import com.example.medibridge.model.Store;
import com.example.medibridge.service.CustomerMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/customer/medicines")

public class CustomerMedicineController {

    @Autowired
    private CustomerMedicineService customerMedicineService;

    @GetMapping("/meds")
    public List<CustomerResponseDTO> getmeds(){
        return customerMedicineService.getallmeds();
    }

    @PostMapping("/search/medicine")
    public List<Store> getStoresByMedBrandName(@RequestBody CustomerRequestDTO customerRequestDTO){
        return customerMedicineService.getStoresByMedicine(customerRequestDTO);
    }

    @PostMapping("/exists")
    public boolean isMedicineAvailable(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerMedicineService.isMedicineAvailable(customerRequestDTO);
    }
    @GetMapping("/{medicineId}/alternates")
    public List<CustomerResponseDTO> getAlternates(@PathVariable int medicineId) {
        return customerMedicineService.getAlternatesForMedicine(medicineId);
    }


}
