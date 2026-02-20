package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;

import java.util.List;

public interface EmployeeService {
    public Employee CheckEmpLogin(String Username, String Password);
    public String registerEmployee(Employee employee);
    public String updateEmployeeProfile(Employee employee);
    public Employee findEmployeeById(Long id);
    public Employee findEmployeeByUsername(String username);
    public Employee findEmployeeByEmail(String email);
    public Employee findEmployeeByPhone(String phone);
    public List<Employee> viewAllEmployees();
    public String updateAccountStatus(Long id,String status);
    public List<Duty> viewAssingnDuties(Long id);


    public String generateResetToken(String email);
    public boolean validateResetToken(String token);
    public boolean changePassword(Employee employee, String oldPassword, String newPassword);
    public void updatePassword(String token,String newPassword);
    public void deleteResetToken(String token);
    public boolean isTokenExpired(String token);

}
