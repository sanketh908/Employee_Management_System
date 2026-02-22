package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Leave;

import java.util.List;

public interface LeaveService {
    public Leave applyLeaveByEmployee(Leave leave, Integer id);
    public List<Leave> viewLeaveStatusByEmployee(Integer id);
    public List<Leave> ViewAllPendingLeave();
    public Leave ApplyLaveByManager(Leave leave,Integer managerId);
    public  List<Leave> ViweAllLeaveByManager(Integer managerId);
    public String updateLeaveStatus(Integer leaveId,String status);

}
