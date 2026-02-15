package com.Sanketh.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "leave_table")
public class Leave {
    @Id
    @Column(name = "leave_id")
    private int leaveId;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)

    private String reason;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;
}
