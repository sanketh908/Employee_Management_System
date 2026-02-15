package com.Sanketh.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "duty_table")
public class Duty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "duty_tiyle",nullable = false)
    private String title;
    @Column(name = "duty_description",nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "assiendbymanager")
    private Manager assiendbymanager;

    @ManyToOne
    @JoinColumn(name = "assiendbyadmin")
    private Admin assiendbyadmin;
}

