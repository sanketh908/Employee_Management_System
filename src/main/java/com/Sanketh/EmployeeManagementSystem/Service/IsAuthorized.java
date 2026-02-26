package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Security.JWTUtilizer;
import org.springframework.stereotype.Service;

@Service
public class IsAuthorized {
    private final JWTUtilizer jwtUtilizer;

    public IsAuthorized(JWTUtilizer jwtUtilizer) {
        this.jwtUtilizer = jwtUtilizer;
    }
    public boolean isAuthorized(String header,String requiredRole) {
        String token = header.substring(7);
        if(jwtUtilizer.validateToken(token).get("role").equalsIgnoreCase(requiredRole)) {
            return true;
        }
        return false;

    }
}
