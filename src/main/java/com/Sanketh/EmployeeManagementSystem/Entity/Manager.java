package com.Sanketh.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "manager_table")
public class Manager {
    @Id
    private int id;
    @Column(name = "manager_name",nullable = false)
    private String username;
    @Column(name = "manager_email",nullable = false,unique = true)
    private String email;
    @Column(name = "manager_password",nullable = false,unique = true)
    private String password;
    @Column(name = "manager_department",nullable = false)
    private String department;
    @Column(name="manager_contact",unique = true,nullable = false)
    private String contact;

    @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
    @JoinColumn()
    private List<Employee> employees;
    @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
    private List<Duty> DutysAssined;

}
