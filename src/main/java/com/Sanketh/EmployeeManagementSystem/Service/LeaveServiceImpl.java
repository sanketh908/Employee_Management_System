package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Leave;
import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.LeaveRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LeaveServiceImpl  implements  LeaveService {
    private final LeaveRepository leaveRepository;
    private  final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;

    public LeaveServiceImpl(LeaveRepository leaveRepository, EmployeeRepository employeeRepository, ManagerRepository managerRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public Leave applyLeaveByEmployee(Leave leave, Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            leave.setEmployee(employee.get());
            leave.setStatus("Pending");
            return leaveRepository.save(leave);
        }
        return null;
    }

    @Override
    public List<Leave> viewLeaveByEmployee(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return leaveRepository.findByEmployeeId(employee.get().getId());
        }
        return List.of();
    }

    @Override
    public List<Leave> ViewAllPendingLeave() {
        return leaveRepository.findByStatusIgnoreCase("Pending");
    }

    @Override
    public Leave applyLaveByManager(Leave leave, Integer managerId) {
        Optional<Manager> manager = managerRepository.findById(managerId);
        if (manager.isPresent()) {
            leave.setManager(manager.get());
            leave.setStatus("Pending");
            return leaveRepository.save(leave);
        }
        return null;
    }

    @Override
    public List<Leave> viewLeavesByManager(Integer managerId) {
        Optional<Manager> manager = managerRepository.findById(managerId);
        if (manager.isPresent()) {
            return leaveRepository.findByManagerId(manager.get().getId());
        }
        return List.of();
    }


    @Override
    public String updateLeaveStatus(Integer leaveId, String status) {
          Optional<Leave> leave = leaveRepository.findById(leaveId);
          if (leave.isPresent()) {
                Leave existingLeave = leave.get();
                existingLeave.setStatus(status);
                leaveRepository.save(existingLeave);
                return "Leave status updated successfully.";
            } else {
                return "Leave not found.";
          }
        }
    }
