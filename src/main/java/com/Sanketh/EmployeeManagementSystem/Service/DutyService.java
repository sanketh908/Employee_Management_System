package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;

import java.util.List;

public interface DutyService {
    public Duty assignDutyByAdminToEmployee(Duty duty, Integer employeeId,Integer adminId);
    public Duty assignDutyByAdminToManager(Duty duty, Integer managerId,Integer adminId);
    public Duty assignDutyByManagerToEmployee(Duty duty, Integer employeeId,Integer managerId);
    public List<Duty> viewAllDutyofManager(Integer managerId);
    public List<Duty> viewAllDutyofEmployee(Integer employeeId);
    public List<Duty> viewDutiesAssignedByAdmin(Integer adminId);
    public List<Duty> viewDutiesAssignedByManager(Integer managerId);

}
