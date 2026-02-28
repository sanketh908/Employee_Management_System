package com.Sanketh.EmployeeManagementSystem.Controller;

import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Security.JWTUtilizer;
import com.Sanketh.EmployeeManagementSystem.Service.EmployeeService;
import com.Sanketh.EmployeeManagementSystem.Service.IsAuthorized;
import com.Sanketh.EmployeeManagementSystem.Service.LeaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@PostMapping(value = "/addemployee" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<String> resisterEmployee(@RequestParam String name,
                                               @RequestParam String gender,
                                               @RequestParam int age,
                                               @RequestParam String designation,
                                               @RequestParam String department,
                                               @RequestParam double salary,
                                               @RequestParam String username,
                                               @RequestParam String email,
                                               @RequestParam String contact,
                                               @RequestParam MultipartFile photo){
    try{
        Employee employee = new Employee();
        employee.setName(name);
        employee.setGender(gender);
        employee.setAge(age);
        employee.setDesignation(designation);
        employee.setDepartment(department);
        employee.setSalary(salary);
        employee.setUsername(username);
        employee.setEmail(email);
        employee.setContact(contact);
        employee.setProfilePicture(photo.getBytes());
        return new ResponseEntity<>(employeeService.registerEmployee(employee), HttpStatus.OK);
    }
    catch (Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
@GetMapping("/viewprofile")
public ResponseEntity<?> viewProfile(@RequestHeader("Authorization") String authHeader,@RequestParam Integer id) {
    if (!isAuthorized.isAuthorized(authHeader, "employee".toUpperCase())) {
        return new ResponseEntity<>("Access Denied ! Need Employee privileges", HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.OK);
}
@GetMapping("/viewdutys")
public ResponseEntity<?> viewDutys(@RequestHeader("Authorization") String authHeader,@RequestParam Integer id) {
    if (!isAuthorized.isAuthorized(authHeader, "employee".toUpperCase())) {
        return new ResponseEntity<>("Access Denied ! Need Employee privileges", HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<>(employeeService.viewAssingnDuties(id), HttpStatus.OK);
}

}
