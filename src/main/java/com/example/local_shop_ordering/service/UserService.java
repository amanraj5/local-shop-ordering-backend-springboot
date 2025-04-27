package com.example.local_shop_ordering.service;

import com.example.local_shop_ordering.dto.LoginRequest;
import com.example.local_shop_ordering.dto.SignUpRequest;
import com.example.local_shop_ordering.model.User;
import com.example.local_shop_ordering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public String addUser(SignUpRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            return "Email is already registered";
        }

        String encodePassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encodePassword);
        user.setPhone(request.getPhone());

        userRepository.save(user);

        return "User registered successfully.";
    }

    public boolean authenticateUser(LoginRequest request) {
        Optional<User> temp = userRepository.findByEmail(request.getEmail());

        if (temp.isPresent()) {
            User user = temp.get();

            boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());
            if (!isPasswordMatch) {
                throw new RuntimeException("Invalid password");
            }

            return true; // Password matched
        }

        // Email not found
        throw new RuntimeException("Invalid email");
    }
}
