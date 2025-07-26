package com.example.medibridge.repository;

import com.example.medibridge.dto.MedicineDTO.Customer.CustomerResponseDTO;
import com.example.medibridge.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
    List<CustomerResponseDTO> findAllByBrandName(String name);

    Medicine findByBrandName(String brandName);

    @Query("SELECT m.alternates FROM Medicine m WHERE m.id = :id")
    List<Medicine> findAlternatesById(@Param("id") Integer id);
}
