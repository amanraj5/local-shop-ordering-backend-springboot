package com.example.local_shop_ordering.service;


import com.example.local_shop_ordering.dto.AdminRequest;
import com.example.local_shop_ordering.model.Admin;
import com.example.local_shop_ordering.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String addAdmin(AdminRequest request){
//        if(adminRepository.existsByEmail(request.getUserId())){
//            return "Email is already registered";
//        }

        String encodePassword = passwordEncoder.encode(request.getPassword());

        Admin admin = new Admin();
        admin.setRole(request.getRole());
        admin.setPassword(encodePassword);
        admin.setEmail(request.getEmail());

        adminRepository.save(admin);

        return "Admin registered successfully.";
    }

    public boolean authenticateUser(AdminRequest request) {
        Optional<Admin> temp = adminRepository.findByEmail(request.getEmail());
//        String role = adminRepository.findByRole(request.getRole());

        if (temp.isPresent()) {
            Admin admin = temp.get();

            boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), admin.getPassword());
            if (!isPasswordMatch) {
                throw new RuntimeException("Invalid password");
            }

            return true; // Password matched
        }

        // Email not found
        throw new RuntimeException("Invalid User");
    }
}
