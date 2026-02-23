package com.Sanketh.EmployeeManagementSystem.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTUtilizer {
        private static final String SECRET_KEY_STRING="am9pbmFzaWRldGVsbGhlYXJpbmdkcm9wcGVkcG93ZXJmdWxleGFtaW5lY2F1Z2h0a2U=";
        private final SecretKey secretKey= Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
        public String generateJWTTokens(String username, String password){
            Map<String,Object> claims=new HashMap<>();
            claims.put("username",username);
            claims.put("password",password);
            return Jwts.builder().claim();
        }
}

