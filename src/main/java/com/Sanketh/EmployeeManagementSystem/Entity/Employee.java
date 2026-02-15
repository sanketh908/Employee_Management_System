package com.Sanketh.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Employee_table")
public class Employee {
    @Id

   private long  id;
    @Column(name = "emp_name", nullable = false)
   private  String name;
    @Column(name = "emp_gender",nullable = false)
    private String gender;
    @Column(name = "emp_age",nullable = false)
   private  int age;
    @Column(name = "emp_salary",nullable = false)
    private double salary;
    @Column(name = "emp_dept",nullable = false)
    private String department;
    @Column(name = "emp_username",nullable = false)
    public  String username;
    @Column(name = "emp_email",nullable = false)
    private String email;
    @Column(name = "emp_password",nullable = false)
    private String password;




    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Leave> leaves;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    public List<Duty>  dutys;



}
