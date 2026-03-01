package com.Sanketh.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "duty_table")
public class Duty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "duty_title",nullable = false)
    private String title;
    @Column(name = "duty_description",nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "Manager_id")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "assigned_by_manager")
    private Manager assignedByManager;

    @ManyToOne
    @JoinColumn(name = "assigned_by_admin")
    private Admin assignedByAdmin;
}

