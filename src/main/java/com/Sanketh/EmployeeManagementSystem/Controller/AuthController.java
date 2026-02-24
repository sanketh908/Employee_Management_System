package com.Sanketh.EmployeeManagementSystem.Controller;

import com.Sanketh.EmployeeManagementSystem.DataTransferringObject.LoginRequest;
import com.Sanketh.EmployeeManagementSystem.Entity.Admin;
import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Service.AdminServiceImpl;
import com.Sanketh.EmployeeManagementSystem.Service.EmployeeServiceImpl;
import com.Sanketh.EmployeeManagementSystem.Service.ManagerService;
import com.Sanketh.EmployeeManagementSystem.Service.ManagerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
public class AuthController {
    private final AdminServiceImpl adminService;
    private  final EmployeeServiceImpl employeeService;
    private  final ManagerServiceImpl managerService;

    public AuthController(AdminServiceImpl adminService, EmployeeServiceImpl employeeService, ManagerServiceImpl managerService) {
        this.adminService = adminService;
        this.employeeService = employeeService;
        this.managerService = managerService;
    }

    @GetMapping("/")
    public String home(){
        return "Employee Management System API is running successfully!!";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        String identifier = loginRequest.getIdentifier();
        String Password = loginRequest.getPassword();
        Admin admin= adminService.checkAdminlogin(identifier,Password);
        Manager manager= managerService.checkManagerlogin(identifier,Password);
        Employee employee= employeeService.CheckEmpLogin(identifier,Password);
    }
}
