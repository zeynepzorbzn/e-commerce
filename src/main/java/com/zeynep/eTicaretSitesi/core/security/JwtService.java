package com.zeynep.eTicaretSitesi.core.security;
import com.zeynep.eTicaretSitesi.core.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    public String generateToken(User user) {
        return Jwts.builder().subject(user.getEmail()).claim("role", user.getRole().getName().name()).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration)).signWith(getSigningKey()).compact();
    }
    public String extractUsername(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }
    public Date extractExpiration(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getExpiration();
    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public boolean isTokenValid(String token, UserDetails user) {
            return extractUsername(token).equals(user.getUsername()) && !isTokenExpired(token);}

    public String extractRole(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload()
                .get("role", String.class);
    }



}
