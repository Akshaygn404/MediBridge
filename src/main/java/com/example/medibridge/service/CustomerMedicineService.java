package com.example.medibridge.service;

import com.example.medibridge.dto.MedicineDTO.Customer.CustomerRequestDTO;
import com.example.medibridge.dto.MedicineDTO.Customer.CustomerResponseDTO;
import com.example.medibridge.model.Medicine;
import com.example.medibridge.model.Store;
import com.example.medibridge.repository.MedicineRepository;
import com.example.medibridge.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class CustomerMedicineService {

    @Autowired
    private MedicineRepository medicineRepository;
    private StoreRepository storeRepository;

    public List<CustomerResponseDTO> getallmeds(){
        List<Medicine> medicineList=medicineRepository.findAll();
        List<CustomerResponseDTO> responseDTOList=new ArrayList<>();
        for(Medicine med: medicineList){
            CustomerResponseDTO customerResponseDTO=new CustomerResponseDTO();
            customerResponseDTO.setId(med.getId());
            customerResponseDTO.setBrandName(med.getBrandName());
            customerResponseDTO.setGenericName(med.getGenericName());
            customerResponseDTO.setDosageForm(med.getDosageForm());
            responseDTOList.add(customerResponseDTO);
        }
        return responseDTOList;

    }
    public List<CustomerResponseDTO> getMedByName(String name){
        return medicineRepository.findAllByBrandName(name);
    }
    public List<Store> getallStores(){
        return storeRepository.findAll();
    }
    public List<Store> getStoresByMedicine(CustomerRequestDTO customerRequestDTO){
        String brandName= customerRequestDTO.getBrandName();
        Medicine medicine=medicineRepository.findByBrandName(brandName);
        if(medicine==null) return new ArrayList<>();
        return new ArrayList<>(medicine.getStores());

    }
    public boolean isMedicineAvailable(CustomerRequestDTO customerRequestDTO) {
        return medicineRepository.findByBrandName(customerRequestDTO.getBrandName()) != null;
    }

    public List<CustomerResponseDTO> getAlternatesForMedicine(int medicineId) {
        Medicine medicine = medicineRepository.findById(medicineId).orElse(null);
        if (medicine == null) return Collections.emptyList();

        Set<Medicine> alternates = medicine.getAlternates();
        List<CustomerResponseDTO> responseList = new ArrayList<>();

        for (Medicine alt : alternates) {
            CustomerResponseDTO dto = new CustomerResponseDTO();
            dto.setId(alt.getId());
            dto.setBrandName(alt.getBrandName());
            dto.setGenericName(alt.getGenericName());
            dto.setDosageForm(alt.getDosageForm());
            responseList.add(dto);
        }

        return responseList;
    }

}
