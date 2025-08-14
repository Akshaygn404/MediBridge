package com.example.medibridge.service;

//import com.example.medibridge.controller.AuthCountroller;
import com.example.medibridge.dto.MedicineDTO.Owner.MedicineRequestDTO;
import com.example.medibridge.dto.MedicineDTO.Owner.MedicineResponseDTO;
import com.example.medibridge.dto.MedicineDTO.Owner.MedicineUpdateDTO;
import com.example.medibridge.model.Medicine;
import com.example.medibridge.model.Owner;
import com.example.medibridge.model.Store;
import com.example.medibridge.repository.MedicineRepository;
import com.example.medibridge.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private StoreRepository storeRepository;

    public Boolean addMeds(int storeId, MedicineRequestDTO dto) {

        Owner currentOwner=authService.getCurrentOwner();
        Store store=storeRepository.findById(storeId).orElse(null);

        if(store==null || currentOwner.getId()!=store.getOwner().getId()) return false;


        Medicine med=new Medicine();
        med.setBrandName(dto.getBrandName());
        med.setGenericName(dto.getGenericName());
        med.setDosageForm(dto.getDosageForm());
        med.setPrice(dto.getPrice());
        med.setStockQuantity(dto.getStockQuantity());


        Medicine savedMed = medicineRepository.save(med);


        store.getMedicines().add(savedMed);
        storeRepository.save(store);
        return true;

    }

    public List<MedicineResponseDTO> getAllmeds(int storeId) {
        Owner currentOwner=authService.getCurrentOwner();
        Store store=storeRepository.findById(storeId).orElse(null);

        if(store==null || currentOwner.getId()!=store.getOwner().getId()) return new ArrayList<>();

        // Use repository to fetch medicines by storeId
        List<Medicine> medicines = medicineRepository.findByStoreId(storeId);

        return medicines.stream().map(med -> {
            MedicineResponseDTO dto = new MedicineResponseDTO();
            dto.setId(med.getId());
            dto.setBrandName(med.getBrandName());
            dto.setGenericName(med.getGenericName());
            dto.setPrice(med.getPrice());
            dto.setStockQuantity(med.getStockQuantity());
            return dto;
        }).collect(Collectors.toList());
    }

    public boolean updateMeds(int storeId, int med_id, MedicineUpdateDTO dto) {
        Owner currentOwner = authService.getCurrentOwner();
        Store store = storeRepository.findById(storeId).orElse(null);

        if (store == null || currentOwner.getId() != store.getOwner().getId()) return false;


        Medicine med = null;
        for (Medicine temp : store.getMedicines()) {
            if (temp.getId() == med_id) {
                med = temp;
                break;
            }
        }

        if (med == null) return false;


        if (dto.getBrandName() != null) med.setBrandName(dto.getBrandName());
        if (dto.getGenericName() != null) med.setGenericName(dto.getGenericName());
        if (dto.getDosageForm() != null) med.setDosageForm(dto.getDosageForm());
        if (dto.getPrice() != 0) med.setPrice(dto.getPrice() );
        if (dto.getStockQuantity() != 0) med.setStockQuantity(dto.getStockQuantity());

        medicineRepository.save(med);
        return true;
    }


    public boolean deleteMeds(int storeId, int medId) {
        Owner currentOwner = authService.getCurrentOwner();
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null || store.getOwner().getId() != currentOwner.getId()) return false;

        Medicine med = medicineRepository.findById(medId).orElse(null);
        if (med == null || !store.getMedicines().contains(med)) return false;

        // Remove relationship
        store.getMedicines().remove(med);
        storeRepository.save(store); // Save updated store-medicine mapping

        return true;
    }

}
