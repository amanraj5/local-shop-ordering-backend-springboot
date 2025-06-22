package com.example.local_shop_ordering.controller;

import com.example.local_shop_ordering.dto.LoginRequest;
import com.example.local_shop_ordering.dto.SignUpRequest;
import com.example.local_shop_ordering.model.User;
import com.example.local_shop_ordering.security.JWTUtil;
import com.example.local_shop_ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;


//    @PostMapping("/signup")
//    public ResponseEntity<String> register(@RequestBody SignUpRequest request){
//        String result = userService.addUser(request);
//
//        if(result.equals("Email is already registered")){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//        }
//
//        return ResponseEntity.ok(result);
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        try {
            String token = userService.addUser(request);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        boolean isAuthenticated = userService.authenticateUser(request);

        if (isAuthenticated) {
            String token = jwtUtil.generateToken(request.getEmail());
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "email", request.getEmail()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid email or password"));
        }
    }

}
