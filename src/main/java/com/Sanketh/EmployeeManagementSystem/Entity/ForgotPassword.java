package com.Sanketh.EmployeeManagementSystem.Entity;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class ForgotPassword {
    private String email;
    public String newPassword;

}
