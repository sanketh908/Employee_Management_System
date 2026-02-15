package com.Sanketh.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Entity
@Slf4j
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String recipient;
    private String subject;
    @Column(nullable = false, length = 1000)
    private String massage;
    private LocalDateTime date;
    @Column(nullable = false)
    private String status;
}
