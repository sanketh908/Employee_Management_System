package com.Sanketh.EmployeeManagementSystem.Controller;

import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Security.JWTUtilizer;
import com.Sanketh.EmployeeManagementSystem.Service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/admin")
@CrossOrigin("*")
public class AdminController {
    private final JWTUtilizer jwtUtilizer;
    private  final AdminService adminService;


    public AdminController(JWTUtilizer jwtUtilizer, AdminService adminService) {
        this.jwtUtilizer = jwtUtilizer;

        this.adminService = adminService;
    }

    @PostMapping("/addmanager")
    public ResponseEntity<?> addManager(@RequestBody Manager manager, @RequestHeader("Authorization") String authHeader){
    String token = authHeader.substring(7);
    if(!jwtUtilizer.validateToken(token).get("role").equalsIgnoreCase("Admin")){
       new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);
    }
    else
    {
        return "Unauthorized access";


    }
}
