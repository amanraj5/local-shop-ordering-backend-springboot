package com.example.local_shop_ordering.controller;

import com.example.local_shop_ordering.dto.LoginRequest;
import com.example.local_shop_ordering.dto.SignUpRequest;
import com.example.local_shop_ordering.model.User;
import com.example.local_shop_ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody SignUpRequest request){
        String result = userService.addUser(request);

        if(result.equals("Email is already registered")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.ok(result);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        boolean isAuthenticated = userService.authenticateUser(request);

        if(isAuthenticated){
            return ResponseEntity.ok("Login Successful");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
