package com.example.assesment.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static io.jsonwebtoken.Claims.EXPIRATION;

@Component
public class JwtUtil{


    // SECRET KEY — only server knows this!
    // minimum 32 characters for HS256!
    // NEVER share this! NEVER put in git!
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }



    public String generateToken(String name){  // return String token, Username goes in

        return Jwts.builder()  //starts building the token
                .setSubject(name) //who this token is for, "sahib" goes here!!
                .setIssuedAt(new Date()) //  current time
                .setExpiration( new Date(System.currentTimeMillis() + expiration)) //  current time + 10 hours
                .signWith(getKey()) //  sign with SECRET key , creates the signature part
                .compact(); //  //builds final token string , eyJhbGci.eyJzdWIi.xyz123
    }
    public String extractUsername(String token){

        return Jwts.parserBuilder() // starts reading the token
                .setSigningKey(getKey())      // same SECRET key, to verify signature
                .build()
                .parseClaimsJws(token)     //    reads and verifies token,throws exception if fake/expired!
                .getBody()       //gets the payload part
                .getSubject();  //gets "sub" field = username we stored!

    }
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            //       if token is:
            //       → fake      → throws exception
            //       → expired   → throws exception
            //       → valid     → no exception ✅
            return true;   // valid token ✅
        } catch (Exception e){
            return false; //Invalid token
        }

    }
}
