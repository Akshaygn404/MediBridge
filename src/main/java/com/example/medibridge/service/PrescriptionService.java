package com.example.medibridge.service;

import com.example.medibridge.dto.Prescription.PrescriptionRequestDTO;
import com.example.medibridge.model.Customer;
import com.example.medibridge.model.Prescription;
import com.example.medibridge.repository.CustomerRepository;
import com.example.medibridge.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private CustomerRepository customerRepository;

    public boolean addPrescp(PrescriptionRequestDTO prescriptionRequestDTO){
        Customer currentCustomer=authService.getCurrentCustomer();
        Prescription prep=new Prescription();
        prep.setMedName(prescriptionRequestDTO.getMedName());
        prep.setDosageForm(prescriptionRequestDTO.getDosageForm());
        prep.setQuantity(prescriptionRequestDTO.getQuantity());
        prep.setMorning(prescriptionRequestDTO.getMorning());
        prep.setNight(prescriptionRequestDTO.getNight());
        prep.setNoon(prescriptionRequestDTO.getNoon());
        prep.setCustomer(currentCustomer);
        List<Prescription> currPresc=currentCustomer.getPrescriptionList();
        currPresc.add(prep);
        currentCustomer.setPrescriptionList(currPresc);


        prescriptionRepository.save(prep);
        return true;


    }

    public List<Prescription> getPrescp() {
        Customer currentCustomer=authService.getCurrentCustomer();
        return currentCustomer.getPrescriptionList();
    }


    public boolean updatePrescp(PrescriptionRequestDTO prescriptionRequestDTO,int id) {
        Customer currentCustomer=authService.getCurrentCustomer();
        List<Prescription> prescriptionList=currentCustomer.getPrescriptionList();
        Prescription precp=null;
        int index=0;
        for(Prescription prescription:prescriptionList){
            if(prescription.getId()==id){
                precp=prescription;
                break;

            }
            index++;

        }
        if(precp==null) return false;


        if(prescriptionRequestDTO.getNight()!=0) precp.setNight(prescriptionRequestDTO.getNight());
        if(prescriptionRequestDTO.getMorning()!=0) precp.setMorning(prescriptionRequestDTO.getMorning());
        if(prescriptionRequestDTO.getNoon()!=0) precp.setNoon(prescriptionRequestDTO.getNoon());
        if(!prescriptionRequestDTO.getDosageForm().equals("")) precp.setDosageForm(prescriptionRequestDTO.getDosageForm());
        if(prescriptionRequestDTO.getQuantity()!=0) precp.setQuantity(prescriptionRequestDTO.getQuantity());
        if(!prescriptionRequestDTO.getMedName().equals("")) precp.setMedName(prescriptionRequestDTO.getMedName());
        prescriptionList.set(index,precp);
        currentCustomer.setPrescriptionList(prescriptionList);
        prescriptionRepository.save(precp);
        return true;


    }

    public boolean delPrescp(int prescId) {
        Customer currentCustomer = authService.getCurrentCustomer();
        List<Prescription> currPrecpList = currentCustomer.getPrescriptionList();

        Prescription prescp = null;
        for (Prescription prescription : currPrecpList) {
            if (prescription.getId() == prescId) {
                prescp = prescription;
                break;
            }
        }

        if (prescp == null) {
            return false;
        }

        currPrecpList.remove(prescp);
        currentCustomer.setPrescriptionList(currPrecpList);


        prescriptionRepository.delete(prescp);

        return true;
    }

}
