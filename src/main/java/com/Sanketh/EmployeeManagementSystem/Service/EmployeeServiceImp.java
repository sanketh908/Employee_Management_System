package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Entity.ResetToken;
import com.Sanketh.EmployeeManagementSystem.Repository.DutyRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ResetTokenRepository;
import com.Sanketh.EmployeeManagementSystem.UtiltyClass.GenaraateRandomId;
import com.Sanketh.EmployeeManagementSystem.UtiltyClass.RandomPasswordGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImp implements EmployeeService{
    private final  EmployeeRepository employeeRepository;
    private final DutyRepository dutyRepository;
    private final ResetTokenRepository resetTokenRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository, DutyRepository dutyRepository, ResetTokenRepository resetTokenRepository) {
        this.employeeRepository = employeeRepository;
        this.dutyRepository = dutyRepository;
        this.resetTokenRepository = resetTokenRepository;
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
            return dutyRepository.findByEmployee(employee.get());
        }
        return Collections.emptyList();
    }

    @Override
    public String generateResetToken(String email) {
        Employee employee= employeeRepository.findByEmail(email);
        if(employee!=null){
            String token= UUID.randomUUID().toString();
            ResetToken resetToken=new ResetToken();
            resetToken.setToken(token);
            resetToken.setEmail(email);
            resetToken.setIssuedAt(LocalDateTime.now());
            resetToken.setExpiresAt(LocalDateTime.now().plusMinutes(5));
            resetTokenRepository.save(resetToken);
            return token;
        }
        return null ;

    }

    @Override
    public boolean validateResetToken(String token) {

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
