package com.Sanketh.EmployeeManagementSystem.Repository;

import com.Sanketh.EmployeeManagementSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
  public Employee findEmployeeByUsernameIgnoreCase(String username);

  Employee findByUsernameAndPassword(String username, String password);
  Employee findByUsername(String username);

  Optional<Employee> findByEmail(String email);

}
