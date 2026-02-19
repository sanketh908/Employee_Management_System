package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.*;

import java.util.List;

public interface AdminService {
    public Admin checkAdminlogin(String username, String password);
    public Manager addManager(Manager manager);
    public Manager checkManagerlogin(String username, String password);
    public List<Manager> getAllManagers();
    public String deleteManager(int id);
    public List<Employee> getAllEmployees();

    String deleteEmployee(int id);

    public long managerCount();
    public long employeeCount();
    public String assigndutyToManager(Duty duty, int managerId);
    public List<Leave>getAllLeavesApplication();
    public String assigndutyToEmployee(Employee employee,int managerId);

}
