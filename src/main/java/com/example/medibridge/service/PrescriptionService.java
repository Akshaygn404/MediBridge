package com.example.medibridge.service;

import com.example.medibridge.dto.Prescription.PrescriptionRequestDTO;
import com.example.medibridge.model.Prescription;
import com.example.medibridge.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public boolean addPrescp(PrescriptionRequestDTO prescriptionRequestDTO){
        Prescription prep=new Prescription();
        prep.setMedName(prescriptionRequestDTO.getMedName());
        prep.setDosageForm(prescriptionRequestDTO.getDosageForm());
        prep.setQuantity(prescriptionRequestDTO.getQuantity());
        prep.setMorning(prescriptionRequestDTO.getMorning());
        prep.setNight(prescriptionRequestDTO.getNight());
        prep.setNoon(prescriptionRequestDTO.getNoon());
        prescriptionRepository.save(prep);
        return true;


    }
}
