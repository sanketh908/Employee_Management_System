package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import com.Sanketh.EmployeeManagementSystem.Entity.Leave;
import com.Sanketh.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.LeaveRepository;
import com.Sanketh.EmployeeManagementSystem.Repository.ManagerRepository;

import java.util.List;
import java.util.Optional;

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
    public List<Leave> viewLeaveStatusByEmployee(Integer id) {
        return List.of();
    }

    @Override
    public List<Leave> ViewAllPendingLeave() {
        return List.of();
    }

    @Override
    public Leave ApplyLaveByManager(Leave leave, Integer managerId) {
        return null;
    }

    @Override
    public List<Leave> ViweAllLeaveByManager(Integer managerId) {
        return List.of();
    }

    @Override
    public String updateLeaveStatus(Integer leaveId, String status) {
        return "";
    }
}
