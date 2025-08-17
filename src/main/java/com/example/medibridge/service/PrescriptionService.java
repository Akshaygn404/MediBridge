package com.example.medibridge.service;

import com.example.medibridge.dto.Prescription.CustomerSummaryDTO;
import com.example.medibridge.dto.Prescription.PrescriptionItemDTO;
import com.example.medibridge.dto.Prescription.PrescriptionRequestDTO;
import com.example.medibridge.dto.Prescription.PrescriptionResponseDTO;
import com.example.medibridge.model.Customer;
import com.example.medibridge.model.Prescription;
import com.example.medibridge.model.PrescriptionItem;
import com.example.medibridge.repository.PrescriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private AuthService authService;

    // 🔹 Convert Prescription -> DTO
    private PrescriptionResponseDTO convertToDTO(Prescription prescription) {
        Customer customer = prescription.getCustomer();

        CustomerSummaryDTO customerDTO = new CustomerSummaryDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );

        List<PrescriptionItemDTO> items = prescription.getItems()
                .stream()
                .map(item -> new PrescriptionItemDTO(
                        item.getId(),
                        item.getMedName(),
                        item.getDosageForm(),
                        item.getQuantity(),
                        item.getMorning(),
                        item.getNoon(),
                        item.getNight()
                ))
                .collect(Collectors.toList());

        return new PrescriptionResponseDTO(
                prescription.getId(),
                prescription.getDoctorName(),
                prescription.getDateIssued(),
                customerDTO,
                items
        );
    }

    // 🔹 Get all prescriptions (DTOs)
    public List<PrescriptionResponseDTO> getPrescriptions() {
        Customer customer = authService.getCurrentCustomer();
        return customer.getPrescriptionList()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Get prescription by ID
    public PrescriptionResponseDTO getPrescription(int id) {
        Customer customer = authService.getCurrentCustomer();

        return customer.getPrescriptionList()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
    }

    // 🔹 Delete prescription
    public boolean deletePrescription(int id) {
        Customer customer = authService.getCurrentCustomer();

        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        if (!Objects.equals(prescription.getCustomer().getId(), customer.getId())) {
            throw new RuntimeException("You do not have permission to delete this prescription.");
        }

        prescriptionRepository.delete(prescription);
        return true;
    }

    // 🔹 Add prescription
    public PrescriptionResponseDTO addPrescription(PrescriptionRequestDTO dto) {
        Customer customer = authService.getCurrentCustomer();

        Prescription prescription = new Prescription();
        prescription.setDoctorName(dto.getDoctorName());
        prescription.setDateIssued(dto.getDateIssued());
        prescription.setCustomer(customer);

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
                item.setPrescription(prescription);
                items.add(item);
            }
        }

        prescription.setItems(items);
        Prescription saved = prescriptionRepository.save(prescription);
        return convertToDTO(saved);
    }

    // 🔹 Update prescription
    @Transactional
    public PrescriptionResponseDTO updatePrescription(int id, PrescriptionRequestDTO dto) {
        Customer customer = authService.getCurrentCustomer();

        Prescription existing = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        if (!Objects.equals(existing.getCustomer().getId(), customer.getId())) {
            throw new RuntimeException("You do not have permission to update this prescription.");
        }

        existing.setDoctorName(dto.getDoctorName());
        existing.setDateIssued(dto.getDateIssued());

        Map<Integer, PrescriptionItem> existingItemsMap = existing.getItems()
                .stream()
                .collect(Collectors.toMap(PrescriptionItem::getId, item -> item));

        List<PrescriptionItem> finalItems = new ArrayList<>();

        if (dto.getItems() != null) {
            for (PrescriptionItemDTO itemDto : dto.getItems()) {
                if (itemDto.getId() != null && existingItemsMap.containsKey(itemDto.getId())) {
                    // update existing
                    PrescriptionItem item = existingItemsMap.get(itemDto.getId());
                    item.setMedName(itemDto.getMedName());
                    item.setDosageForm(itemDto.getDosageForm());
                    item.setQuantity(itemDto.getQuantity());
                    item.setMorning(itemDto.getMorning());
                    item.setNoon(itemDto.getNoon());
                    item.setNight(itemDto.getNight());
                    finalItems.add(item);
                } else {
                    // add new
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

        Prescription updated = prescriptionRepository.save(existing);
        return convertToDTO(updated);
    }
}
