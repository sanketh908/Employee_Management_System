package com.Sanketh.EmployeeManagementSystem.DataTransferringObject;

import lombok.Data;

@Data
public class LoginRequest {
    private String identifier; // Can be username or email
    private String password;




}
