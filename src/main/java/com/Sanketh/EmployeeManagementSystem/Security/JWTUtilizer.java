package com.Sanketh.EmployeeManagementSystem.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
        public Map<String,String> validateToken(String token){
            Map<String,String> res=new HashMap<>();
            try{
               Claims claims= Jwts
                        .parserBuilder()
                        .setSigningKey(getKey())
                        .build()
                        .parseClaimsJws(token)
                .getBody();
                res.put("username",claims.get("username",String.class));
                res.put("role",claims.get("role",String.class));
                res.put("code","200");

            }catch (ExpiredJwtException e){
                res.put("code","401");
                res.put("Error","Token is invalid or expired Login again");
            }
            return res;
        }

}

