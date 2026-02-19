package com.Sanketh.EmployeeManagementSystem.Repository;

import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {


    Optional<Manager> findByusernameAndpassword(String username, String password);
    Optional<Manager> findManagerByUsername(String username);
     Optional<Manager>  findManagerByEmail(String email);
}
