package com.example.medibridge.repository;

import com.example.medibridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByname(String username);


    Optional<User> findByEmail(String email);
}
