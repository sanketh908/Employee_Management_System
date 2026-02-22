package com.Sanketh.EmployeeManagementSystem.Repository;

import com.Sanketh.EmployeeManagementSystem.Entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Integer> {
    List<Leave> findByEmployeeId(Integer id);
    List<Leave> findByStatusIgnoreCase(String status);
    List<Leave> findByManagerId(Integer id);
}
