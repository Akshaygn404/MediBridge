package com.example.medibridge.dto.Prescription;

import com.example.medibridge.model.Prescription;
import com.example.medibridge.model.PrescriptionItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionResponseDTO {
    private int id;
    private String doctorName;
    private String dateIssued;
    private CustomerSummaryDTO customer;
    private List<PrescriptionItemDTO> items;



//    public List<PrescriptionItemDTO> getItems() {
//        return items;
//    }
//
//    public void setItems(List<PrescriptionItemDTO> items) {
//        this.items = items;
//    }
//
//    public String getDateIssued() {
//        return dateIssued;
//    }
//
//    public void setDateIssued(String dateIssued) {
//        this.dateIssued = dateIssued;
//    }
//
//    public String getDoctorName() {
//        return doctorName;
//    }
//
//    public void setDoctorName(String doctorName) {
//        this.doctorName = doctorName;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//




    // Convert Entity -> DTO
    public static PrescriptionResponseDTO fromEntity(Prescription prescription) {
        PrescriptionResponseDTO dto = new PrescriptionResponseDTO();
        dto.setId(prescription.getId());
        dto.setDoctorName(prescription.getDoctorName());
        dto.setDateIssued(prescription.getDateIssued());

        if (prescription.getItems() != null) {
            dto.setItems(
                    prescription.getItems().stream()
                            .map(PrescriptionItemDTO::fromEntity)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }
}
