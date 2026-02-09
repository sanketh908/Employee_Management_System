package com.Sanketh.demo.Repository;


import com.Sanketh.demo.Entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.function.Function;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
   Employee
}
