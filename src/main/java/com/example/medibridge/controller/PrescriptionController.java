package com.example.medibridge.controller;

import com.example.medibridge.dto.Prescription.PrescriptionRequestDTO;
import com.example.medibridge.dto.Prescription.PrescriptionResponseDTO;
import com.example.medibridge.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/prescription")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping("/add")
    public ResponseEntity<PrescriptionResponseDTO> addPrescription(@RequestBody PrescriptionRequestDTO dto) {
        return ResponseEntity.ok(prescriptionService.addPrescription(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PrescriptionResponseDTO> updatePrescription(
            @PathVariable int id,
            @RequestBody PrescriptionRequestDTO dto) {
        return ResponseEntity.ok(prescriptionService.updatePrescription(id, dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PrescriptionResponseDTO>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getPrescriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionResponseDTO> getPrescription(@PathVariable int id) {
        return ResponseEntity.ok(prescriptionService.getPrescription(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable int id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.ok("Prescription deleted successfully");
    }
}
