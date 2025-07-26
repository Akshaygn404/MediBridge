package com.example.medibridge.service;

import com.example.medibridge.model.Customer;
import com.example.medibridge.model.Owner;
import com.example.medibridge.model.User;
import com.example.medibridge.repository.OwnerRepository;
import com.example.medibridge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OwnerRepository ownerRepository;




    public boolean registerNewUser(String name, String email, String password, String phone, String role) {
        if(userRepository.existsByEmail(email)) return false;

        String encryptedPassword= passwordEncoder.encode(password);
        if(role.equals("owner")) {

            Owner owner = new Owner();
            owner.setName(name);
            owner.setEmail(email);
            owner.setPassword(encryptedPassword);
            owner.setPhone(phone);
            owner.setRole("ROLE_OWNER");

            userRepository.save(owner);
        }
        else{
            Customer customer=new Customer();
            customer.setName(name);
            customer.setEmail(email);
            customer.setPassword(encryptedPassword);
            customer.setPhone(phone);
            customer.setRole("ROLE_CUSTOMER");
            userRepository.save(customer);
        }
        return true;

    }


    public Owner getCurrentOwner() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return ownerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Owner not found"));
    }




}
