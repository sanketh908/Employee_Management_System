package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;

import java.util.List;

public interface ManagerService {
    public Manager checkManagerlogin(String username, String password);
    public Manager findManagerById(Integer id);
    public Manager findManagerByUsername(String username);
    public Manager findManagerByEmail(String email);
    public List<Manager> viewAllManagers();
    public List<Employee> viewAllEmployees();
    public String updateEmployeeAccountStatus(Integer id,String status);
    public String generateResetToken(String email);
    public List<Duty> viewAssingnDuties(Integer id);
    public boolean validateResetToken(String token);
    public boolean changePassword(Manager manager,String oldPassword,String newPassword);
    public void updatePassword(String token,String newPassword);
    public void deleteResetToken(String token);
    public boolean isTokenExpired(String token);
}
