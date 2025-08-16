package com.example.medibridge.controller;

import com.example.medibridge.dto.Prescription.PrescriptionRequestDTO;
import com.example.medibridge.model.Prescription;
import com.example.medibridge.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer/prescription")
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @PostMapping("/add")
    String addPrescription(@RequestBody PrescriptionRequestDTO prescriptionRequestDTO){
        boolean a=prescriptionService.addPrescp(prescriptionRequestDTO);
        return "Added Successfully";

    }
    @GetMapping("/")
    List<Prescription> getPrescription(){
        return prescriptionService.getPrescp();
    }

    @PutMapping("/update/{presc_id}")
    List<Prescription> updatePrescription(@RequestBody PrescriptionRequestDTO prescriptionRequestDTO,@PathVariable int presc_id){
        return (prescriptionService.updatePrescp(prescriptionRequestDTO,presc_id))?prescriptionService.getPrescp():new ArrayList<Prescription>();
    }
    @DeleteMapping("/delete/{presc_id}")
    List<Prescription> deletePrescription(@PathVariable int presc_id){
        return (prescriptionService.delPrescp(presc_id))?prescriptionService.getPrescp():new ArrayList<>();
    }



}
