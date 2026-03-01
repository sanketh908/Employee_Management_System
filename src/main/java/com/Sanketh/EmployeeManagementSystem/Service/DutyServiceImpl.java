package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Admin;
import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Repository.AdminRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.DutyRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DutyServiceImpl implements  DutyService {
    private final  DutyRepository dutyRepository;
    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;
    private final ManagerRepository managerRepository;

    public DutyServiceImpl(DutyRepository dutyRepository, EmployeeRepository employeeRepository, AdminRepository adminRepository, ManagerRepository managerRepository) {
        this.dutyRepository = dutyRepository;
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public Duty assignDutyByAdminToEmployee(Duty duty, Integer employeeId, Integer adminId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Admin> admin = adminRepository.findById(adminId);
        if (employee.isPresent() && admin.isPresent()) {
            duty.setEmployee(employee.get());
            duty.setAssignedByAdmin(admin.get());
            return dutyRepository.save(duty);
        }
        return null;
    }

    @Override
    public Duty assignDutyByAdminToManager(Duty duty, Integer managerId, Integer adminId) {
        Optional<Manager> manager = managerRepository.findById(managerId);
        Optional<Admin> admin = adminRepository.findById(adminId);
        if (manager.isPresent() && admin.isPresent()) {
            duty.setManager(manager.get());
            duty.setAssignedByAdmin(admin.get());
            return dutyRepository.save(duty);
        }
        return null;
    }

    @Override
    public Duty assignDutyByManagerToEmployee(Duty duty, Integer employeeId, Integer managerId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Manager> manager = managerRepository.findById(managerId);
        if (employee.isPresent() && manager.isPresent()) {
            duty.setEmployee(employee.get());
            duty.setAssignedByManager(manager.get());
            return dutyRepository.save(duty);
        }
        return null;
    }

    @Override
    public List<Duty> viewAllDutyofManager(Integer managerId) {
        return dutyRepository.findByManagerId(managerId);
    }

    @Override
    public List<Duty> viewAllDutyofEmployee(Integer employeeId) {
        return dutyRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Duty> viewDutiesAssignedByAdmin(Integer adminId) {
        return dutyRepository.findByAssignedByAdminId(adminId);
    }

    @Override
    public List<Duty> viewDutiesAssignedByManager(Integer managerId) {
        return dutyRepository.findByAssignedByManagerId(managerId);
    }
}
