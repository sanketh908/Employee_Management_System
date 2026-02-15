package com.Sanketh.EmployeeManagementSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "duty_table")
public class Duty {
    @Id
    private int id;
}
