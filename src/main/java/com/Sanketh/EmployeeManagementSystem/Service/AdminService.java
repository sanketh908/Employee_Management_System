package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.*;

import java.util.List;

public interface AdminService {
    public Admin checkAdminlogin(String username,String password);
    public Manager addManager(Manager manager);
    public List<Manager> getAllManagers();
    public String deleteManager();
    public List<Employee> getAllEmployees();
    public String deleteEmployee();
    public long managerCount();
    public long employeeCount();
    public String assigndutyToManager(Duty duty,int managerId);
    public String assigndutyToEmployee(Employee employee,int managerId);
     public List<Leave>   getAllLeavesApplication();

}
