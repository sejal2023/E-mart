package com.example.Config;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtProvider {

	private final SecretKey key;

    public JwtProvider(SecretKey key) {
        this.key = key;
    }

    public String generateToken(Authentication auth) {
        List<String> roles = auth.getAuthorities().stream()
                                 .map(GrantedAuthority::getAuthority)
                                 .toList();

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000L)) 
                .claim("email", auth.getName())
                .claim("authorities", roles) 
                .signWith(key)
                .compact();
    }


    public String getEmailFromToken(String jwt) {
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7); 
        }
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            return claims.get("email", String.class);
        } 
        catch (ExpiredJwtException e) {
            System.out.println("Token has expired.");
            return null;
        } 
        catch (Exception e) {
            System.out.println("Invalid token.");
            return null;
        }
    }

}
