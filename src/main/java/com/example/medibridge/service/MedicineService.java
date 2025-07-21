package com.example.medibridge.service;

import com.example.medibridge.controller.AuthCountroller;
import com.example.medibridge.model.Medicine;
import com.example.medibridge.model.Owner;
import com.example.medibridge.model.Store;
import com.example.medibridge.repository.MedicineRepository;
import com.example.medibridge.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private StoreRepository storeRepository;

    public Boolean addMeds(int storeId, String brandName, String genericName, String dosageForm, double price, int stockQuantity) {

        Owner currentOwner=authService.getCurrentOwner();
        Store store=storeRepository.findById(storeId).orElse(null);

        if(store==null || currentOwner.getId()!=store.getOwner().getId()) return false;

        Medicine med=new Medicine();
        med.setBrandName(brandName);
        med.setGenericName(genericName);
        med.setDosageForm(dosageForm);
        med.setPrice(price);
        med.setStockQuantity(stockQuantity);


        Medicine savedMed = medicineRepository.save(med);


        store.getMedicines().add(savedMed);
        storeRepository.save(store);
        return true;

    }

    public List<Medicine> getAllmeds(int storeId) {
        Owner currentOwner=authService.getCurrentOwner();
        Store store=storeRepository.findById(storeId).orElse(null);

        if(store==null || currentOwner.getId()!=store.getOwner().getId()) return new ArrayList<>();

        return store.getMedicines();
    }

    public boolean updateMeds(int storeId, String brandName, String genericName, String dosageForm, double price, int stockQuantity, int med_id) {
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


        if (brandName != null) med.setBrandName(brandName);
        if (genericName != null) med.setGenericName(genericName);
        if (dosageForm != null) med.setDosageForm(dosageForm);
        if (price != 0) med.setPrice(price);
        if (stockQuantity != 0) med.setStockQuantity(stockQuantity);

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
