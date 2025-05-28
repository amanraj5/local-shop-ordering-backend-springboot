package com.example.local_shop_ordering.controller;
import com.example.local_shop_ordering.dto.AdminRequest;
import com.example.local_shop_ordering.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addAdmin")
    public ResponseEntity<String> register(@RequestBody AdminRequest request){
        String result = adminService.addAdmin(request);

//        if(result.equals("Email is already registered")){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> getAdmin(@RequestBody AdminRequest adminRequest){
        boolean isAuthenticated = adminService.authenticateUser(adminRequest);

        if(isAuthenticated){
            return ResponseEntity.ok("Login Successfull");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User");
        }
    }
}
