package com.Sanketh.demo.Entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "manager_table")
public class Manager {
    private long id;
    private String name;
    private String email;
    private String password;
    private String department;
    private String contact;

    @OneToMany(mappedBy = "Manager",cascade = CascadeType.ALL)
    private List<Employee> employeeList;
    @OneToMany(mappedBy = "Manager",cascade = CascadeType.ALL)
    private List<Duty> dutyList;
}
