package com.example.medibridge.controller;

import com.example.medibridge.dto.MedicineDTO.MedicineRequestDTO;
import com.example.medibridge.dto.MedicineDTO.MedicineResponseDTO;
import com.example.medibridge.dto.MedicineDTO.MedicineUpdateDTO;
import com.example.medibridge.model.Medicine;
import com.example.medibridge.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner/stores/{storeId}/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/meds")
    private List<MedicineResponseDTO> getAllMedicines(@PathVariable int storeId){
        return medicineService.getAllmeds(storeId);
    }

    @PostMapping("/add")
    public String addMeds(@PathVariable int storeId, @RequestBody MedicineRequestDTO medicineRequestDTO){
        return (medicineService.addMeds(storeId,medicineRequestDTO))?"Medicine added successfully":"Failed to add medicine";
    }

    @PutMapping("/update/{med_id}")
    public String updateMedicine(@PathVariable int storeId, @PathVariable int med_id, @RequestBody MedicineUpdateDTO medicineUpdateDTO){
        return (medicineService.updateMeds(storeId,med_id,medicineUpdateDTO))?"Updated Successfully":"Updation failed";
    }

    @DeleteMapping("/delete/{med_id}")
    public String deleteMedicine(@PathVariable int storeId,@PathVariable int med_id){
        return (medicineService.deleteMeds(storeId,med_id))?"Successfully deleted":"Deletion failed";
    }




}
