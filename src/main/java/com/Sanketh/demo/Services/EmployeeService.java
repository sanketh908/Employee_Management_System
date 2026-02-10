package com.Sanketh.demo.Services;

import com.Sanketh.demo.Entity.Employee;
import com.Sanketh.demo.Repository.EmployeeRepository;

public class EmployeeService {
    private final EmployeeRepository  employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public void saveEntry(Employee employee)
    {
        employeeRepository.save(employee);
    }
    public Employee findone(String name)
    {
      return   employeeRepository.findEmployeeByname(name);
    }
}
