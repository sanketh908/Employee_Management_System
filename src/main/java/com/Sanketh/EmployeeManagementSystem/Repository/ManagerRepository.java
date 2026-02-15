package com.Sanketh.EmployeeManagementSystem.Repository;

import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {
}
