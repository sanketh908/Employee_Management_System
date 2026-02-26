package com.Sanketh.EmployeeManagementSystem.Controller;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Security.JWTUtilizer;
import com.Sanketh.EmployeeManagementSystem.Service.AdminService;
import com.Sanketh.EmployeeManagementSystem.Service.DutyService;
import com.Sanketh.EmployeeManagementSystem.Service.EmployeeService;
import com.Sanketh.EmployeeManagementSystem.Service.ManagerService;
import org.apache.tomcat.Jar;
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

    public ManagerController(EmployeeService employeeService, ManagerService managerService, AdminService adminService, DutyService dutyService, JWTUtilizer jwtUtilizer) {

        this.employeeService = employeeService;
        this.managerService = managerService;
        this.adminService = adminService;
        this.dutyService = dutyService;
        this.jwtUtilizer = jwtUtilizer;
    }

    @GetMapping("/viewallduties")
    public ResponseEntity<?> getAllDuty(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        if(!jwtUtilizer.validateToken(token).get("role").equalsIgnoreCase("Manager")) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(managerService., HttpStatus.OK);
    }


}
