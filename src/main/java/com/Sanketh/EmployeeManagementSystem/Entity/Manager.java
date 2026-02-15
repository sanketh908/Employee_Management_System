package com.Sanketh.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "manager_table")
public class Manager {
    private int id;
    private String username;
    private String password;
    private String email;
    private String department;
    private String salary;
    private String contact;

    @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
    @JoinColumn()
    private List<Employee> employees;
    @OneToMany(mappedBy = "manager")
    private List<Duty> DutysAssined;

}
