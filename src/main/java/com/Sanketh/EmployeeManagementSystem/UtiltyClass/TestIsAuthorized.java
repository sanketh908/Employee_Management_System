package com.Sanketh.EmployeeManagementSystem.UtiltyClass;

import com.Sanketh.EmployeeManagementSystem.Security.JWTUtilizer;
import lombok.experimental.UtilityClass;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class TestIsAuthorized {


    @Autowired
    private   JWTUtilizer jwtUtilizer;



    public  boolean  isAuthorized(String header,String expectedRole) {
        if (header == null || !header.startsWith("Bearer ")) {
            String token = header.substring(7);
            return jwtUtilizer.validateToken(token).get("role").equalsIgnoreCase(expectedRole);

    }

}
