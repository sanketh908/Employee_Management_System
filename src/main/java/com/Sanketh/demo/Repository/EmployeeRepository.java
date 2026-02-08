package com.Sanketh.demo.Repository;

import com.Sanketh.demo.Entity.Admin;
import com.Sanketh.demo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    findByName
}
