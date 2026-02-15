package com.Sanketh.EmployeeManagementSystem.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "leave_table")
public class Leave {
    @Id
    @Column(name = "leave_id")
    private int leaveId;
}
