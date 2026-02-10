package com.Sanketh.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection = "Employee")
public class Employee {
    @Id

    private long  id;

    private String name;

    private String gender;

    private int age ;

    private String  designation;

    private String department;

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
