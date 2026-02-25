package com.Sanketh.EmployeeManagementSystem.Controller;

import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Security.JWTUtilizer;
import com.Sanketh.EmployeeManagementSystem.Service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/admin")
@CrossOrigin("*")
public class AdminController {
    private final JWTUtilizer jwtUtilizer;
    private final AdminService adminService;


    public AdminController(JWTUtilizer jwtUtilizer, AdminService adminService) {
        this.jwtUtilizer = jwtUtilizer;

        this.adminService = adminService;
    }

    @PostMapping("/addmanager")
    public ResponseEntity<?> addManager(@RequestBody Manager manager, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        if (!jwtUtilizer.validateToken(token).get("role").equalsIgnoreCase("Admin")) {
            new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);
        }


        adminService.addManager(manager);
        return new ResponseEntity<>("Manager added successfully,With ID :" + manager.getId(), HttpStatus.OK);


    }

    @GetMapping("/viewallmanagers")
    public ResponseEntity<List<Manager>> viewAllManagers(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        if (!jwtUtilizer.validateToken(token).get("role").equalsIgnoreCase("Admin")) {
            new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);

        }
        return new ResponseEntity<>(adminService.viewAllManagers(), HttpStatus.OK);


    }
    @GetMapping("/viewallemployees")
    public ResponseEntity<List<Employee>> viewAllEmployee(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        if (!jwtUtilizer.validateToken(token).get("role").equalsIgnoreCase("Admin")) {
            new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);

        }
        return new ResponseEntity<>(adminService.getAllEmployees(), HttpStatus.OK);


    }
}
