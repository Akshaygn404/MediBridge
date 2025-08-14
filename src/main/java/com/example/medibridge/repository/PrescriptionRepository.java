package com.example.medibridge.repository;

import com.example.medibridge.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription,Integer> {
}
