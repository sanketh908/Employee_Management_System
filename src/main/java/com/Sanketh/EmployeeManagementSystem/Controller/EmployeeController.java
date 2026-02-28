package com.Sanketh.EmployeeManagementSystem.Controller;

import com.Sanketh.EmployeeManagementSystem.Security.JWTUtilizer;
import com.Sanketh.EmployeeManagementSystem.Service.EmployeeService;
import com.Sanketh.EmployeeManagementSystem.Service.IsAuthorized;
import com.Sanketh.EmployeeManagementSystem.Service.LeaveService;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {
    private final IsAuthorized isAuthorized;
private final  EmployeeService employeeService;
private final JWTUtilizer jwtUtilizer;

private final LeaveService leaveService;

public EmployeeController(IsAuthorized isAuthorized, EmployeeService employeeService, JWTUtilizer jwtUtilizer, LeaveService leaveService) {
    this.isAuthorized = isAuthorized;
    this.employeeService = employeeService;
    this.jwtUtilizer = jwtUtilizer;
    this.leaveService = leaveService;
}

}
