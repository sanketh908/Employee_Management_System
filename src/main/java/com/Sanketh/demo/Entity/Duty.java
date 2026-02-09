package com.Sanketh.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "duty_table")
public class Duty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false,length = 3000)
    private String description;
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "assignedbymanager")
    private Manager assinedbymanager;
    @ManyToOne
    @JoinColumn(name = "assignedbyadmin")
    private Admin assinedbyadmin;

}
