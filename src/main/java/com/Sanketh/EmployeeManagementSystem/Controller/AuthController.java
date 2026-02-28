package com.Sanketh.EmployeeManagementSystem.Controller;

import com.Sanketh.EmployeeManagementSystem.DataTransferringObject.LoginRequest;
import com.Sanketh.EmployeeManagementSystem.Entity.Admin;
import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Security.JWTUtilizer;
import com.Sanketh.EmployeeManagementSystem.Service.AdminServiceImpl;
import com.Sanketh.EmployeeManagementSystem.Service.EmployeeServiceImpl;
import com.Sanketh.EmployeeManagementSystem.Service.ManagerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/api")
@CrossOrigin("*")
public class AuthController {
    private final AdminServiceImpl adminService;
    private final EmployeeServiceImpl employeeService;
    private final ManagerServiceImpl managerService;
    private final JWTUtilizer jwtUtilizer;

    public AuthController(AdminServiceImpl adminService, EmployeeServiceImpl employeeService, ManagerServiceImpl managerService, JWTUtilizer jwtUtilizer) {
        this.adminService = adminService;
        this.employeeService = employeeService;
        this.managerService = managerService;
        this.jwtUtilizer = jwtUtilizer;
    }

    @GetMapping("/")
    public String home() {
        return "Employee Management System API is running successfully!!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String identifier = loginRequest.getIdentifier();
        String Password = loginRequest.getPassword();
        Admin admin = adminService.checkAdminlogin(identifier, Password);
        Manager manager = managerService.checkManagerlogin(identifier, Password);
        Employee employee = employeeService.CheckEmpLogin(identifier, Password);
        if (admin != null) {
            String token = jwtUtilizer.generateJWTTokens(admin.getUsername(), "ADMIN",admin.getId());
            Map<String, Object> res = new HashMap<>();
            res.put("role", "ADMIN");
            res.put("massage", "Admin login successful");
            res.put("token", token);
            res.put("username", admin.getUsername());
            res.put("data", admin);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
            if (manager != null) {
                String token = jwtUtilizer.generateJWTTokens(manager.getUsername(), "MANAGER",manager.getId());
                Map<String, Object> res = new HashMap<>();
                res.put("role", "MANAGER");
                res.put("massage", "Manager login successful");
                res.put("token", token);
                res.put("username",manager.getUsername());
                res.put("data", manager);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            if (employee != null) {
            if(employee.getAccountants().equalsIgnoreCase("ACCEPTED")){
                    Map<String, Object> res = new HashMap<>();
                    res.put("role", "EMPLOYEE");
                    res.put("massage", "Employee login successful" );
                    res.put("username",employee.getUsername());
                    res.put("data", employee);
                    return new ResponseEntity<>(res, HttpStatus.OK);
                }
            else{
                return new ResponseEntity<>("Employee account is not accepted yet. Please contact Administrator."+employee.getAccountants(), HttpStatus.FORBIDDEN);
            }
            }
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);


    }
}
