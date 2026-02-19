package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ManagerServiceImp implements ManagerService{
    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;

    public ManagerServiceImp(ManagerRepository managerRepository, EmployeeRepository employeeRepository) {
        this.managerRepository = managerRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Manager checkManagerlogin(String username, String password) {
        Optional<Manager> manager= managerRepository.findByusernameAndpassword(username,password);
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
       Optional<Manager> manager = managerRepository.findManagerByUsername(username);
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
        Optional<Manager> manager=managerRepository.findManagerByEmail(email);
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
    public String updateEmployeeAccountStatus(long id, String status) {
        return "";
    }
}
