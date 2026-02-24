package com.Sanketh.EmployeeManagementSystem.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTUtilizer {
    String secretKey="";
        JWTUtilizer() throws NoSuchAlgorithmException {
            KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
            SecretKey SecretKey=keyGenerator.generateKey();
            secretKey= Base64.getEncoder().encodeToString(SecretKey.getEncoded());
        }
        public Key getKey()
        {
                return  Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
         }
        public String generateJWTTokens(String username, String role){
            Map<String,Object> claims=new HashMap<>();
            claims.put("username",username);
            claims.put("role",role);
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 *60*60*2))
                    .signWith(getKey(), SignatureAlgorithm.HS256).compact();
        }
        public boolean validateToken(String token){
            Map<String,Object> claims=new HashMap<>();
            try{
                Jwts
                        .parserBuilder()
                        .setSigningKey(getKey())
                        .build()
                        .parseClaimsJws(token)
                .getBody();

            }catch (Exception e){
                return false;
            }
        }

}

