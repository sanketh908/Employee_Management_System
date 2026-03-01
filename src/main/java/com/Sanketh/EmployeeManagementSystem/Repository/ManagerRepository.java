package com.Sanketh.EmployeeManagementSystem.Repository;

import com.Sanketh.EmployeeManagementSystem.Entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {

    @Query("SELECT m FROM Manager m WHERE m.username = :username AND m.password = :password")
    Optional<Manager> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Optional<Manager> findByUsername(String username);
    Optional<Manager> findByEmail(String email);

}
