package com.Sanketh.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "employee_table")
public class Employee {
    @Id
    @Column(name = "emp_id",unique = true,nullable = false)
    private int id;
    @Column(name = "emp_name",nullable = false)
    private String name;
    @Column( name = "emp_gender",nullable = false)
    private String gender;
    @Column(name = "emp_age",nullable = false)
    private int age ;
    @Column(name = "emp_desingnation",nullable = false)
    private String  designation;
    @Column(name = "emp_department",nullable = false)
    private String department;
    @Column(name = "emp_salary",nullable = false)
    private double salary;
    @Column(name = "emp_contact",nullable = false,unique = true)
    private String contact;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Leave> leaves;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Duty> duties;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
