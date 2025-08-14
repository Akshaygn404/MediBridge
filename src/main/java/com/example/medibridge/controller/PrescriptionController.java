package com.example.medibridge.controller;

import com.example.medibridge.dto.Prescription.PrescriptionRequestDTO;
import com.example.medibridge.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @PostMapping("/prescription")
    String addPrescription(@RequestBody PrescriptionRequestDTO prescriptionRequestDTO){
        boolean a=prescriptionService.addPrescp(prescriptionRequestDTO);
        return "Added Successfully";

    }



}
