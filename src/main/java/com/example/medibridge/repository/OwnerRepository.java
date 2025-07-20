package com.example.medibridge.repository;

import com.example.medibridge.model.Owner;
import com.example.medibridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner,Integer> {
    Optional<Owner> findByEmail(String email);
}
