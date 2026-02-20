package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.UtiltyClass.GenaraateRandomId;
import com.Sanketh.EmployeeManagementSystem.UtiltyClass.RandomPasswordGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService{
    private final  EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee CheckEmpLogin(String username, String password) {
        return employeeRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public String registerEmployee(Employee employee) {
       Integer id= GenaraateRandomId.generateRandomManagerId();
        employee.setId(id);
        String password= RandomPasswordGenerator.geneateRandomPassword(8);
        employee.setPassword(password);
    }

    @Override
    public String updateEmployeeProfile(Employee employee) {
        return "";
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return null;
    }

    @Override
    public Employee findEmployeeByUsername(String username) {
        return null;
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return null;
    }

    @Override
    public Employee findEmployeeByPhone(String phone) {
        return null;
    }

    @Override
    public List<Employee> viewAllEmployees() {
        return List.of();
    }

    @Override
    public String updateAccountStatus(Integer id, String status) {
        return "";
    }

    @Override
    public List<Duty> viewAssingnDuties(Integer id) {
        return List.of();
    }

    @Override
    public String generateResetToken(String email) {
        return "";
    }

    @Override
    public boolean validateResetToken(String token) {
        return false;
    }

    @Override
    public boolean changePassword(Employee employee, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public void updatePassword(String token, String newPassword) {

    }

    @Override
    public void deleteResetToken(String token) {

    }

    @Override
    public boolean isTokenExpired(String token) {
        return false;
    }
}
