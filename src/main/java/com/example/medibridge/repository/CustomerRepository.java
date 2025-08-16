package com.example.medibridge.repository;

import com.example.medibridge.model.Customer;
import com.example.medibridge.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);
}
