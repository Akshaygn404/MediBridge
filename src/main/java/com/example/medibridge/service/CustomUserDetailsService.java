//package com.example.medibridge.service;
//
//import com.example.medibridge.model.CustomUserDetails;
//import com.example.medibridge.model.User;
//import com.example.medibridge.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user=userRepository.findByname(username)
//                .orElseThrow(()->new UsernameNotFoundException("User not found"));
//
//
//
//        return new CustomUserDetails(user);
//
//
//    }
//}
