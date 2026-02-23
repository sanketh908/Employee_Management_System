package com.Sanketh.EmployeeManagementSystem.Repository;

import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {


    Optional<Manager> findByUsernameAndPassword(String username, String password);
    Optional<Manager> findByUsername(String username);
     Optional<Manager>  findByEmail(String email);

}
