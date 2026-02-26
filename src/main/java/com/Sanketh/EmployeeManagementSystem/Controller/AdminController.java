package com.Sanketh.EmployeeManagementSystem.Controller;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Entity.Leave;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Security.JWTUtilizer;
import com.Sanketh.EmployeeManagementSystem.Service.AdminService;
import com.Sanketh.EmployeeManagementSystem.Service.IsAuthorized;
import com.Sanketh.EmployeeManagementSystem.Service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/admin")
@CrossOrigin("*")
public class AdminController {
    private final IsAuthorized isAuthorized;
    private final AdminService adminService;
    private final JWTUtilizer jwtUtilizer;
    private final ManagerService  managerService;



    public AdminController(IsAuthorized isAuthorized, AdminService adminService, JWTUtilizer jwtUtilizer, ManagerService managerService) {
        this.isAuthorized = isAuthorized;
        this.adminService = adminService;
        this.jwtUtilizer = jwtUtilizer;
        this.managerService = managerService;
    }

    @PostMapping("/addmanager")
    public ResponseEntity<?> addManager(@RequestBody Manager manager, @RequestHeader("Authorization") String authHeader) {

        if (!isAuthorized.isAuthorized(authHeader,"Admin")) {
            return new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);
        }


        adminService.addManager(manager);
        return new ResponseEntity<>("Manager added successfully,With ID :" + manager.getId(), HttpStatus.OK);


    }

    @GetMapping("/viewallmanagers")
    public ResponseEntity<?> viewAllManagers(@RequestHeader("Authorization") String authHeader) {//hear I put wildcard pattern because this methode can return a list of managers or string
        if (!isAuthorized.isAuthorized(authHeader,"Admin")) {
           return new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);

        }
        return new ResponseEntity<>(adminService.viewAllManagers(), HttpStatus.OK);


    }
    @GetMapping("/viewallemployees")
    public ResponseEntity<?> viewAllEmployee(@RequestHeader("Authorization") String authHeader) {//hear I put wildcard pattern because this methode can return a list of employees or string

        if (!isAuthorized.isAuthorized(authHeader,"Admin")) {
           return new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);

        }
        return new ResponseEntity<>(adminService.getAllEmployees(), HttpStatus.OK);


    }
    @PutMapping("/assigndutytomanager")
    public ResponseEntity<String> assignDutyToManager(@RequestBody Duty duty, @RequestHeader("Authorization") String authHeader) {

        if (!isAuthorized.isAuthorized(authHeader,"Admin")) {
            return new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);

        }
        duty.setAssiendByAdmin(adminService.checkAdminlogin(jwtUtilizer.validateToken(authHeader).get("username"), null));
        return new ResponseEntity<>("Duty assigned successfully to Manager with ID: " + duty.getManager().getId(), HttpStatus.OK);
    }
    @PutMapping("/updateemployeestutes")
    public ResponseEntity<String> updateEmployeeStatus(@RequestParam int empire, @RequestHeader("Authorization") String authHeader, @RequestParam String stuts) {

        if (!isAuthorized.isAuthorized(authHeader,"Admin")) {
           return new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);

        }
       String message = managerService.updateEmployeeAccountStatus(empire,stuts.toUpperCase());
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
    @PutMapping("/assigndutytoemployee")
    public ResponseEntity<String> assignDutyToEmployee(@RequestBody Duty duty, @RequestHeader("Authorization") String authHeader) {

        if (!isAuthorized.isAuthorized(authHeader,"Admin")) {
           return new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);

        }
        duty.setAssiendByAdmin(adminService.checkAdminlogin(jwtUtilizer.validateToken(authHeader).get("username"), jwtUtilizer.validateToken(authHeader).get("password")));
        return new ResponseEntity<>("Duty assigned successfully to Employee with ID: " + duty.getEmployee().getId(), HttpStatus.OK);

    }
    @GetMapping("/viewallleaveapplications")
    public ResponseEntity<?>viewAllLeaveApplications(@RequestHeader("Authorization") String authHeader) {

        if (!isAuthorized.isAuthorized(authHeader,"Admin")) {
            return new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);
        }
        List<Leave> leaves=adminService.getAllLeavesApplication();
        return new ResponseEntity<>(leaves, HttpStatus.OK);
    }
    @DeleteMapping("/deleteemployee")
    public ResponseEntity<String> deleteEmployee(@RequestParam int eid,@RequestHeader("Authorization") String authHeader,@RequestParam String empid) {

        if (!isAuthorized.isAuthorized(authHeader,"Admin")) {
            return new ResponseEntity<>("Access Denied ! Need Admin privileges",HttpStatus.FORBIDDEN);
        }
        String massage = adminService.deleteEmployee(eid);
        return   new ResponseEntity<>(massage + eid, HttpStatus.OK);
    }
    @GetMapping("/viewmanagerscount")
    public ResponseEntity<?> viewManagersCount(@RequestHeader("Authorization") String authHeader) {

        if (!isAuthorized.isAuthorized(authHeader,"Admin")) {
            return new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);
        }
        long count = adminService.managerCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @GetMapping("/viewemployeescount")
    public ResponseEntity<?> viewEmployeesCount(@RequestHeader("Authorization") String authHeader) {

        if (!isAuthorized.isAuthorized(authHeader,"Admin")) {
            return new ResponseEntity<>("Access Denied ! Need Admin privileges", HttpStatus.FORBIDDEN);
        }
        long count = adminService.employeeCount();
        return new ResponseEntity<>(count, HttpStatus.OK);



    }


}
