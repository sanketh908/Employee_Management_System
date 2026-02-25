package com.Sanketh.EmployeeManagementSystem.Controller;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Security.JWTUtilizer;
import com.Sanketh.EmployeeManagementSystem.Service.AdminService;
import com.Sanketh.EmployeeManagementSystem.Service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/admin")
@CrossOrigin("*")
public class AdminController {
    private final JWTUtilizer jwtUtilizer;
    private final AdminService adminService;
    private final ManagerService  managerService;


    public AdminController(JWTUtilizer jwtUtilizer, AdminService adminService, ManagerService managerService) {
        this.jwtUtilizer = jwtUtilizer;

        this.adminService = adminService;
        this.managerService = managerService;
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
    @PutMapping("/assignduty")
    public ResponseEntity<String> assignDutyToManager(@RequestBody Duty duty, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        if (!jwtUtilizer.validateToken(token).get("role").equalsIgnoreCase("Admin")) {
            new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);

        }
        duty.setAssiendByAdmin(adminService.checkAdminlogin(jwtUtilizer.validateToken(token).get("username"), null));
        Duty assignedDuty = adminService.assigndutyToManager(duty, duty.getManager().getId());
        if (assignedDuty != null) {
            return new ResponseEntity<>("Duty assigned successfully to Manager with ID: " + duty.getManager().getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to assign duty. Please check the manager ID and try again.", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateEmployeeStatus(@RequestParam int empid, @RequestHeader("Authorization") String authHeader,@RequestParam String stuts) {
        String token =authHeader.substring(7);
        if (!jwtUtilizer.validateToken(token).get("role").equalsIgnoreCase("Admin")) {
            new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);

        }
       String meassage= managerService.updateEmployeeAccountStatus(empid,stuts.toUpperCase());
        return new ResponseEntity<>(meassage, HttpStatus.OK);

    }
}
