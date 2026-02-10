package com.Sanketh.demo.Repository;


import com.Sanketh.demo.Entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.function.Function;

public interface EmployeeRepository extends  {
         List<Employee> findBynameIgnoreCase();

   @Override
   <S extends Employee> S save(S entity);
}
