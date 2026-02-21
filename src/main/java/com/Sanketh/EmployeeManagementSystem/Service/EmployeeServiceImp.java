package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Repository.DutyRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.UtiltyClass.GenaraateRandomId;
import com.Sanketh.EmployeeManagementSystem.UtiltyClass.RandomPasswordGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService{
    private final  EmployeeRepository employeeRepository;
    private final DutyRepository dutyRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository, DutyRepository dutyRepository) {
        this.employeeRepository = employeeRepository;
        this.dutyRepository = dutyRepository;
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
        employee.setAccountstats("Pending");
        employee.setRole("Employee");
        employeeRepository.save(employee);
        return "Employee registered successfully";
    }

    @Override
    public String updateEmployeeProfile(Employee employee) {
        employeeRepository.save(employee);
        return "Employee profile updated successfully";
    }

    @Override
    public Employee findEmployeeById(Integer id) {
       return employeeRepository.findById(id).orElse(null);

    }

    @Override
    public Employee findEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }



    @Override
    public List<Employee> viewAllEmployees() {

        return  employeeRepository.findAll();
    }

    @Override
    public String updateAccountStatus(Integer id, String status) {

        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            employee.get().setAccountstats(status);
            employeeRepository.save(employee.get());
            return "Status updated to "+status;
        }
        return "Employee profile not found";
    }

    @Override
    public List<Duty> viewAssingnDuties(Integer id) {
        Optional <Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return dutyRepository.
        }
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
