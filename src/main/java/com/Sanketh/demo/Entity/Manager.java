package com.Sanketh.demo.Entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "manager_table")
public class Manager {
    @Id
    private long id;
    @Column(name = "manager_name",nullable = false)
    private String name;
    @Column(name = "manager_name",nullable = false)
    private String username;
    @Column(name = "manager_email",nullable = false,unique = true)
    private String email;
    @Column(name = "manager_password",nullable = false)
    private String password;
    @Column(name = "manager_department",nullable = false)
    private String department;
    @Column(name = "manager_contact",nullable = false,unique = true)
    private String contact;

    @OneToMany(mappedBy = "Manager",cascade = CascadeType.ALL)
    private List<Employee> employeeList;
    @OneToMany(mappedBy = "Manager",cascade = CascadeType.ALL)
    private List<Duty> dutyList;
}
