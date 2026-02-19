package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.*;

import java.util.List;

public interface AdminService {
    public Admin checkAdminlogin(String username, String password);
    public Manager addManager(Manager manager);
    public Manager checkManagerlogin(String username, String password);
    public List<Manager> getAllManagers();
    public String deleteManager(Integer id);
    public List<Employee> getAllEmployees();

    String deleteEmployee(Long id);

    public long managerCount();
    public long employeeCount();
    public String assigndutyToManager(Duty duty, Long managerId);
    public List<Leave>getAllLeavesApplication();
    public String assigndutyToEmployee(Employee employee,Long managerId);

}
