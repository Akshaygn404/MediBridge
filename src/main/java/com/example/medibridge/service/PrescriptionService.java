package com.example.medibridge.service;


import com.example.medibridge.dto.Prescription.PrescriptionItemDTO;
import com.example.medibridge.dto.Prescription.PrescriptionRequestDTO;
import com.example.medibridge.model.Customer;
import com.example.medibridge.model.Prescription;
import com.example.medibridge.model.PrescriptionItem;
import com.example.medibridge.repository.PrescriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    @Autowired
    private  PrescriptionRepository prescriptionRepository;

    @Autowired
    private AuthService authService;


    public List<Prescription> getPrescriptions() {
        Customer customer=authService.getCurrentCustomer();
        return customer.getPrescriptionList();
    }

    public Prescription getPrescription(Long id) {
        Customer customer=authService.getCurrentCustomer();
        List<Prescription> li= customer.getPrescriptionList();
        for(Prescription prescription:li){
            if(prescription.getId()==id) return prescription;
        }
        throw new RuntimeException("Prescription not found");


    }
    public boolean deletePrescription(Long id) {
        Customer customer = authService.getCurrentCustomer();

        // Find the prescription by ID
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        // Ensure the prescription belongs to the current customer
        if (prescription.getCustomer().getId()!=customer.getId()) {
            throw new RuntimeException("You do not have permission to delete this prescription.");
        }

        // Perform the delete
        prescriptionRepository.delete(prescription);

        return true;
    }


    public Prescription addPrescription(PrescriptionRequestDTO dto) {
        // Get the current logged-in customer
        Customer customer = authService.getCurrentCustomer();

        // Build the Prescription entity
        Prescription prescription = new Prescription();
        prescription.setDoctorName(dto.getDoctorName());
        prescription.setCustomer(customer);

        // Convert each PrescriptionItemDTO into a PrescriptionItem entity
        List<PrescriptionItem> items = new ArrayList<>();
        if (dto.getItems() != null) {
            for (PrescriptionItemDTO itemDto : dto.getItems()) {
                PrescriptionItem item = new PrescriptionItem();
                item.setMedName(itemDto.getMedName());
                item.setDosageForm(itemDto.getDosageForm());
                item.setQuantity(itemDto.getQuantity());
                item.setMorning(itemDto.getMorning());
                item.setNoon(itemDto.getNoon());
                item.setNight(itemDto.getNight());
                item.setPrescription(prescription); // Set back-reference
                items.add(item);
            }
        }

        prescription.setItems(items);

        // Save prescription and items (cascade)
        return prescriptionRepository.save(prescription);
    }





    public Prescription updatePrescription(Long id, Prescription updated) {
        Prescription prescription = getPrescription(id);
        prescription.setDoctorName(updated.getDoctorName());
        return prescriptionRepository.save(prescription);
    }

    @Transactional
    public Prescription updatePrescription(Long id, PrescriptionRequestDTO dto) {
        Customer customer = authService.getCurrentCustomer();

        // Fetch the existing prescription
        Prescription existing = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        // Check ownership
        if (existing.getCustomer().getId()!=(customer.getId())) {
            throw new RuntimeException("You do not have permission to update this prescription.");
        }

        // Update doctor name
        existing.setDoctorName(dto.getDoctorName());

        // Create a map of existing items for quick lookup
        Map<Integer, PrescriptionItem> existingItemsMap = existing.getItems().stream()
                .collect(Collectors.toMap(PrescriptionItem::getId, item -> item));

        List<PrescriptionItem> finalItems = new ArrayList<>();

        if (dto.getItems() != null) {
            for (PrescriptionItemDTO itemDto : dto.getItems()) {
                if (itemDto.getId() != null && existingItemsMap.containsKey(itemDto.getId())) {

                    PrescriptionItem item = existingItemsMap.get(itemDto.getId());
                    item.setMedName(itemDto.getMedName());
                    item.setDosageForm(itemDto.getDosageForm());
                    item.setQuantity(itemDto.getQuantity());
                    item.setMorning(itemDto.getMorning());
                    item.setNoon(itemDto.getNoon());
                    item.setNight(itemDto.getNight());
                    finalItems.add(item);
                } else {

                    PrescriptionItem newItem = new PrescriptionItem();
                    newItem.setMedName(itemDto.getMedName());
                    newItem.setDosageForm(itemDto.getDosageForm());
                    newItem.setQuantity(itemDto.getQuantity());
                    newItem.setMorning(itemDto.getMorning());
                    newItem.setNoon(itemDto.getNoon());
                    newItem.setNight(itemDto.getNight());
                    newItem.setPrescription(existing);
                    finalItems.add(newItem);
                }
            }
        }


        existing.getItems().clear();
        existing.getItems().addAll(finalItems);


        return prescriptionRepository.save(existing);
    }


}

