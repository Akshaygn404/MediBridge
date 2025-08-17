package com.example.medibridge.dto.Prescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionResponseDTO {
    private Long id;
    private String doctorName;
    private List<PrescriptionItemDTO> items;
}

