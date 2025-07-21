package com.example.medibridge.controller;

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
    private List<Medicine> getAllMedicines(@PathVariable int storeId){
        return medicineService.getAllmeds(storeId);
    }

    @PostMapping("/add")
    public String addMeds(@PathVariable int storeId, @RequestParam String brandName,@RequestParam String genericName,@RequestParam String dosageForm,@RequestParam double price,@RequestParam int stockQuantity){
        return (medicineService.addMeds(storeId,brandName,genericName,dosageForm,price,stockQuantity))?"Medicine added successfully":"Failed to add medicine";
    }

    @PutMapping("/update/{med_id}")
    public String updateMedicine(@PathVariable int storeId,@RequestParam String brandName,@RequestParam String genericName,@RequestParam String dosageForm,@RequestParam double price,@RequestParam int stockQuantity,@PathVariable int med_id){
        return (medicineService.updateMeds(storeId,brandName,genericName,dosageForm,price,stockQuantity,med_id))?"Updated Successfully":"Updation failed";
    }

    @DeleteMapping("/delete/{med_id}")
    public String deleteMedicine(@PathVariable int storeId,@PathVariable int med_id){
        return (medicineService.deleteMeds(storeId,med_id))?"Successfully deleted":"Deletion failed";
    }




}
