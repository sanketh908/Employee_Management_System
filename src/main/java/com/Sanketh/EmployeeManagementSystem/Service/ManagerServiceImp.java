package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Entity.ResetToken;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ManagerRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ResetTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ManagerServiceImp implements ManagerService{
    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;
    private final ResetTokenRepository resetTokenRepository;

    public ManagerServiceImp(ManagerRepository managerRepository, EmployeeRepository employeeRepository, ResetTokenRepository resetTokenRepository) {
        this.managerRepository = managerRepository;
        this.employeeRepository = employeeRepository;
        this.resetTokenRepository = resetTokenRepository;
    }

    @Override
    public Manager checkManagerlogin(String username, String password) {
        Optional<Manager> manager= managerRepository.findByUsernameAndPassword(username,password);
        if(manager.isPresent()){
            return manager.get();
        }else
        {
            log.info("Manager not found");
            return null;
        }

    }

    @Override
    public Manager findManagerByid(int id) {
        Optional<Manager> manager= managerRepository.findById(id);
        if(manager.isPresent()){
            return manager.get();
        }
        else
        {

            log.info("Manager not found");
            return null;
        }
    }

    @Override
    public Manager findManagerByUsername(String username) {
       Optional<Manager> manager = managerRepository.findByUsername(username);
       if(manager.isPresent()){
           return manager.get();
       }
       else
       {
           log.info("Manager not found");
           return null;
       }
    }

    @Override
    public Manager findManagerByEmail(String email) {
        Optional<Manager> manager=managerRepository.findByEmail(email);
        if(manager.isPresent()){
            return manager.get();
        }else
        {
            log.info("Manager not found");
            return null;
        }
    }

    @Override
    public List<Manager> viewAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public List<Employee> viewAllEmployees() {
        return employeeRepository.findAll();
    }



    @Override
    public String updateEmployeeAccountStatus(Long id, String status) {
       Optional<Employee> employee=employeeRepository.findById(id);
        if(employee.isPresent()){
            Employee emp=employee.get();
            emp.setAccountstats(status);
            employeeRepository.save(emp);
            return "employee account status updated succcessfully";
        }
        else {
            log.info("Employee not found");
            return "employee account status not found";
        }
    }

    @Override
    public String generateResetToken(String email) {
        Optional<Manager> manager=managerRepository.findByEmail(email);
        if(manager.isPresent()){
            String token= UUID.randomUUID().toString();
            resetTokenRepository.s
        }
    }

    @Override
    public boolean validateResetToken(String token) {
        return false;
    }

    @Override
    public boolean changePassword(Manager manager, String oldPassword, String newPassword) {
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
