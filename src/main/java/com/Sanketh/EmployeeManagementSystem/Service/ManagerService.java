package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;

import java.util.List;

public interface ManagerService {
    public Manager checkManagerlogin(String username, String password);
    public Manager findManagerByid(int id);
    public Manager findManagerByUsername(String username);
    public Manager findManagerByEmail(String email);
    public List<Manager> viewAllManagers();
    public List<Employee> viewAllEmployees();
    public String updateEmployeeAccountStatus(Long  id,String status);
    public String generateResetToken(String email);
}
