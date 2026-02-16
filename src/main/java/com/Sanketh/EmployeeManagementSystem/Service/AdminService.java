package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.*;
import com.Sanketh.EmployeeManagementSystem.Repository.AdminRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin checkAdminlogin(String username,String password)
    {
        Optional<Admin> admin= adminRepository.findAdminByUsernameAndPassword(username,password);
        if(admin.isPresent())
        {
            return admin.get();
        }
        else
        {
            return null;
        }
    }
    public Manager addManager(Manager manager)
    {
        int managerId = generateRandomManagerId();
        manager.setId(managerId);

    }
    public List<Manager> getAllManagers();
    public String deleteManager();
    public List<Employee> getAllEmployees();
    public String deleteEmployee();
    public long managerCount();
    public long employeeCount();
    public String assigndutyToManager(Duty duty,int managerId);
    public String assigndutyToEmployee(Employee employee,int managerId);
     public List<Leave>   getAllLeavesApplication();
    private int generateRandomManagerId()
    {
        Random random = new Random();
        return random.nextInt(1000,9999);
    }
}
