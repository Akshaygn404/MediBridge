package com.example.medibridge.dto.Prescription;

// CustomerSummaryDTO.java


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSummaryDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;
}

