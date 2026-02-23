package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;

import java.util.List;

public class DutyServiceImpl implements  DutyService {
    @Override
    public Duty assignDutyByAdminToEmployee(Duty duty, Integer employeeId, Integer adminId) {
        return null;
    }

    @Override
    public Duty assignDutyByAdminToManager(Duty duty, Integer managerId, Integer adminId) {
        return null;
    }

    @Override
    public Duty assignDutyByManagerToEmployee(Duty duty, Integer employeeId, Integer managerId) {
        return null;
    }

    @Override
    public List<Duty> viewAllDutyofManager(Integer managerId) {
        return List.of();
    }

    @Override
    public List<Duty> viewAllDutyofEmployee(Integer employeeId) {
        return List.of();
    }

    @Override
    public List<Duty> viewDutiesAssignedByAdmin(Integer adminId) {
        return List.of();
    }

    @Override
    public List<Duty> viewDutiesAssignedByManager(Integer managerId) {
        return List.of();
    }
}
