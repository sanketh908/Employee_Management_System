package com.Sanketh.EmployeeManagementSystem.Controller;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Entity.Leave;
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
    private final LeaveService leaveService;

    public ManagerController(EmployeeService employeeService, ManagerService managerService, AdminService adminService, DutyService dutyService, JWTUtilizer jwtUtilizer, IsAuthorized isAuthorized, LeaveService leaveService) {

        this.employeeService = employeeService;
        this.managerService = managerService;
        this.adminService = adminService;
        this.dutyService = dutyService;

        this.jwtUtilizer = jwtUtilizer;
        this.isAuthorized = isAuthorized;
        this.leaveService = leaveService;
    }

    @GetMapping("/viewallduties")
    public ResponseEntity<?> getAllDuty(@RequestHeader("Authorization") String authHeader, @RequestParam Integer id) {

        if (!isAuthorized.isAuthorized(authHeader, "manager".toUpperCase())) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.FORBIDDEN);
        }
        List<Duty> duties = managerService.viewAssingnDuties(id);
        return new ResponseEntity<>(duties, HttpStatus.OK);
    }

    @GetMapping("/viewallemployees")
    public ResponseEntity<?> viewAllEmployee(@RequestHeader("Authorization") String authHeader) {

        if (!isAuthorized.isAuthorized(authHeader, "manager".toUpperCase())) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(managerService.viewAllEmployees(), HttpStatus.OK);


    }
    @GetMapping("/viewallmanagers")
    public ResponseEntity<?> viewAllManagers(@RequestHeader("Authorization") String authHeader) {

        if (!isAuthorized.isAuthorized(authHeader, "manager".toUpperCase())) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(managerService.viewAllManagers(), HttpStatus.OK);
    }
    @GetMapping("/updateemployeestatus")
    public ResponseEntity<?> updateEmployeeAccountStatus(@RequestHeader("Authorization") String authHeader, @RequestParam Integer id,@RequestParam String status) {

        if (!isAuthorized.isAuthorized(authHeader, "manager")) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.FORBIDDEN);
        }
        String res = managerService.updateEmployeeAccountStatus(id, status.toUpperCase());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PutMapping("/applyleave")
    public ResponseEntity<?> applyLeave(@RequestHeader("Authorization") String authHeader, @RequestParam Integer managerId, @RequestBody Leave leave) {
        if(!isAuthorized.isAuthorized(authHeader, "manager".toUpperCase())) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.FORBIDDEN);
        }
        Leave res = leaveService.applyLaveByManager(leave, managerId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PutMapping("/viewownleaves")
    public ResponseEntity<?> viewAllOwnLeaves(@RequestHeader("Authorization") String authHeader, @RequestParam Integer managerId) {
        if (!isAuthorized.isAuthorized(authHeader, "manager".toUpperCase())) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.OK);
        }
        List<Leave> res = leaveService.viewLeavesByManager(managerId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PutMapping("/updateleavestatus")
    public ResponseEntity<?> updateLeaveStatus(@RequestHeader("Authorization") String authHeader, @RequestParam Integer leaveId, @RequestParam String status) {
        if (!isAuthorized.isAuthorized(authHeader, "manager".toUpperCase())) {
            return new ResponseEntity<>("Access Denied ! Need Manager privileges", HttpStatus.FORBIDDEN);
        }
        String res = leaveService.updateLeaveStatus(leaveId, status.toUpperCase());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
