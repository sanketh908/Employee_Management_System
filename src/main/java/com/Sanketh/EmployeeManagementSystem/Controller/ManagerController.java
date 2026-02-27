package com.Sanketh.EmployeeManagementSystem.Controller;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Security.JWTUtilizer;
import com.Sanketh.EmployeeManagementSystem.Service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
@CrossOrigin("*")
public class ManagerController {
    private final EmployeeService employeeService;
    private final ManagerService managerService;
    private final AdminService adminService;
    private final DutyService dutyService;
    private final JWTUtilizer jwtUtilizer;
    private final IsAuthorized isAuthorized;

    public ManagerController(EmployeeService employeeService, ManagerService managerService, AdminService adminService, DutyService dutyService, JWTUtilizer jwtUtilizer, IsAuthorized isAuthorized) {

        this.employeeService = employeeService;
        this.managerService = managerService;
        this.adminService = adminService;
        this.dutyService = dutyService;

        this.jwtUtilizer = jwtUtilizer;
        this.isAuthorized = isAuthorized;
    }

    @GetMapping("/viewallduties")
    public ResponseEntity<?> getAllDuty(@RequestHeader("Authorization") String authHeader, @RequestParam Integer id) {

        if (!isAuthorized.isAuthorized(authHeader, "manager")) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.FORBIDDEN);
        }
        List<Duty> duties = managerService.viewAssingnDuties(id);
        return new ResponseEntity<>(duties, HttpStatus.OK);
    }

    @GetMapping("/viewallemployees")
    public ResponseEntity<?> viewAllEmployee(@RequestHeader("Authorization") String authHeader) {

        if (!isAuthorized.isAuthorized(authHeader, "manager")) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(managerService.viewAllEmployees(), HttpStatus.OK);


    }
    @GetMapping("/viewallmanagers")
    public ResponseEntity<?> viewAllManagers(@RequestHeader("Authorization") String authHeader) {

        if (!isAuthorized.isAuthorized(authHeader, "manager")) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(managerService.viewAllManagers(), HttpStatus.OK);
    }
    @GetMapping("/updateemployeestatus")
    public ResponseEntity<?> updateEmployeeAccountStatus(@RequestHeader("Authorization") String authHeader, @RequestParam Integer id,@RequestParam String status) {

        if (!isAuthorized.isAuthorized(authHeader, "manager")) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.FORBIDDEN);
        }
        String res = managerService.updateEmployeeAccountStatus(id, status);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
