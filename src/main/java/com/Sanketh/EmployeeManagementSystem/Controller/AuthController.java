package com.Sanketh.EmployeeManagementSystem.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/api")
public class AuthController {
    @GetMapping("/")
    public String home(){
        return "Employee Management System API is running successfully!!";
    }
}
