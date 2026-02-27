package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.ResetToken;
import com.Sanketh.EmployeeManagementSystem.Repository.DutyRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ResetTokenRepository;
import com.Sanketh.EmployeeManagementSystem.UtiltyClass.GenaraateRandomId;
import com.Sanketh.EmployeeManagementSystem.UtiltyClass.RandomPasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final  EmployeeRepository employeeRepository;
    private final DutyRepository dutyRepository;
    private final ResetTokenRepository resetTokenRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DutyRepository dutyRepository, ResetTokenRepository resetTokenRepository) {
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
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        return employee.orElse(null);
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
            return dutyRepository.findByEmployeeId(employee.get().getId());
        }
        return Collections.emptyList();
    }

    @Override
    public String generateResetToken(String email) {
        Optional<Employee> employee= employeeRepository.findByEmail(email);
        if(employee.isPresent()){
            String token= UUID.randomUUID().toString();
            ResetToken resetToken=new ResetToken();
            resetToken.setToken(token);
            resetToken.setEmail(email);
            resetToken.setCreatedAt(LocalDateTime.now());
            resetToken.setExpiresAt(LocalDateTime.now().plusMinutes(5));
            resetTokenRepository.save(resetToken);
            return token;
        }
        return null ;

    }

    @Override
    public boolean validateResetToken(String token) {
        Optional<ResetToken> resetToken=resetTokenRepository.findByToken(token);
        return resetToken.isPresent() && isTokenExpired(token);
    }

    @Override
    public boolean changePassword(Employee employee, String oldPassword, String newPassword) {

        if(employee.getPassword().equals(oldPassword)){
            employee.setPassword(newPassword);
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }

    @Override
    public void updatePassword(String token, String newPassword) {
        Optional<ResetToken> resetToken=resetTokenRepository.findByToken(token);
        if (resetToken.isPresent() && isTokenExpired(token)) {
            String email=resetToken.get().getEmail();
            Optional<Employee> employee=employeeRepository.findByEmail(email);
            if(employee.isPresent()){
                Employee Emp=employee.get();
                Emp.setPassword(newPassword);
                employeeRepository.save(Emp);
                deleteResetToken(token);
            }
            else
            {
                log.info("Reset token not found");
            }



        }
    }

    @Override
    public void deleteResetToken(String token) {
        resetTokenRepository.deleteByToken(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Optional<ResetToken> resetToken=resetTokenRepository.findByToken(token);
        return resetToken.map(value -> value.getExpiresAt().isBefore(LocalDateTime.now())).orElse(true);
    }
}
