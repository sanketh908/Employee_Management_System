package com.Sanketh.EmployeeManagementSystem.Repository;

import com.Sanketh.EmployeeManagementSystem.Entity.Duty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyRepository extends JpaRepository<Duty,Integer> {
}
