package com.example.medibridge.service;

import com.example.medibridge.model.Customer;
import com.example.medibridge.model.Owner;
import com.example.medibridge.model.User;
import com.example.medibridge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;





    public boolean registerNewUser(String name, String email, String password, String phone, String role) {
        User user=userRepository.findByEmail(email).orElse(null);
        if(user!=null) return false;
        if(role.equals("owner")) {

            Owner owner = new Owner();
            owner.setName(name);
            owner.setEmail(email);
            owner.setPassword(password);
            owner.setPhone(phone);
            owner.setRole(role);

            userRepository.save(owner);


        }
        else{
            Customer customer=new Customer();
            customer.setName(name);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setPhone(phone);
            customer.setRole(role);
            userRepository.save(customer);
        }
        return true;

    }
}
