package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Entity.ResetToken;
import com.Sanketh.EmployeeManagementSystem.Repository.DutyRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ManagerRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ResetTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService{
    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;
    private final ResetTokenRepository resetTokenRepository;

    private final DutyRepository dutyRepository;


    public ManagerServiceImpl(ManagerRepository managerRepository, EmployeeRepository employeeRepository, ResetTokenRepository resetTokenRepository, DutyRepository dutyRepository) {
        this.managerRepository = managerRepository;
        this.employeeRepository = employeeRepository;
        this.resetTokenRepository = resetTokenRepository;

        this.dutyRepository = dutyRepository;
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
    public Manager findManagerById(Integer id) {
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
    public String updateEmployeeAccountStatus(Integer id, String status) {
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
    public List<Duty> viewAssingnDuties(Integer id) {
        Optional<Manager> manager=managerRepository.findById(id);
        if(manager.isPresent()){
           Manager manager1=manager.get();
           return dutyRepository.findByManagerId(manager1.getId());
        }
        else
        {
            log.info("Manager not found");
            return null;
        }
    }

    @Override
    public boolean validateResetToken(String token) {
        Optional<ResetToken> resetToken=resetTokenRepository.findByToken(token);
        return resetToken.isPresent() && !isTokenExpired(token);

    }

    @Override
    public boolean changePassword(Manager manager, String oldPassword, String newPassword) {
        if(manager.getPassword().equals(oldPassword)){
            manager.setPassword(newPassword);
            managerRepository.save(manager);
            return true;
        }
        return false;
    }

    @Override
    public void updatePassword(String token, String newPassword) {
       Optional<ResetToken> resetToken=resetTokenRepository.findByToken(token);
       if (resetToken.isPresent() && !isTokenExpired(token)) {
           String email=resetToken.get().getEmail();
           Optional<Manager> manager=managerRepository.findByEmail(email);
           if(manager.isPresent()){
            Manager manager1=manager.get();
            manager1.setPassword(newPassword);
            managerRepository.save(manager1);
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
