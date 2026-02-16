package com.Sanketh.EmployeeManagementSystem.Repository;

import com.Sanketh.EmployeeManagementSystem.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
 public Optional<Admin> findAdminByUsernameAndPassword(String username,String password);
}
