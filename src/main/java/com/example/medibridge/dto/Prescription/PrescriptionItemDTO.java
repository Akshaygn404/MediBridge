package com.example.medibridge.dto.Prescription;

import com.example.medibridge.model.PrescriptionItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionItemDTO {
    private Integer id;

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMorning() {
        return morning;
    }

    public void setMorning(int morning) {
        this.morning = morning;
    }

    public int getNoon() {
        return noon;
    }

    public void setNoon(int noon) {
        this.noon = noon;
    }

    public int getNight() {
        return night;
    }

    public void setNight(int night) {
        this.night = night;
    }

    private String medName;
    private String dosageForm;
    private int quantity;
    private int morning;
    private int noon;
    private int night;

    // Convert Entity -> DTO
    public static PrescriptionItemDTO fromEntity(PrescriptionItem item) {
        PrescriptionItemDTO dto = new PrescriptionItemDTO();
        dto.setId(item.getId());
        dto.setMedName(item.getMedName());
        dto.setDosageForm(item.getDosageForm());
        dto.setQuantity(item.getQuantity());
        dto.setMorning(item.getMorning());
        dto.setNoon(item.getNoon());
        dto.setNight(item.getNight());
        return dto;
    }
}
