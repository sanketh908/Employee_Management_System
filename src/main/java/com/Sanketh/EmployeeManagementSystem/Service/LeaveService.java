package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Leave;

import java.util.List;

public interface LeaveService {
    public Leave applyLeaveByEmployee(Leave leave, Integer id);
    public List<Leave> viewLeaveByEmployee(Integer id);
    public List<Leave> ViewAllPendingLeave();
    public Leave applyLaveByManager(Leave leave,Integer managerId);
    public  List<Leave> viewLeavesByManager(Integer managerId);
    public String updateLeaveStatus(Integer leaveId,String status);

}
