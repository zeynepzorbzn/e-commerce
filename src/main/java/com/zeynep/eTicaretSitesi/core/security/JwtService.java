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
import com.zeynep.eTicaretSitesi.core.enums.TokenType;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user) {
        return Jwts.builder().subject(user.getEmail()).claim("role", user.getRole().getName().name()).claim("type", TokenType.ACCESS.name())
        .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + accessExpiration)).signWith(getSigningKey()).compact();
    }
    public String generateRefreshToken(User user){
        return Jwts.builder().subject(user.getEmail()).claim("type", TokenType.REFRESH.name()).issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis()+refreshExpiration)).signWith(getSigningKey()).compact();
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
    public String extractType(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().get("type", String.class);
    }
    public boolean isAccessToken(String token) {
        return TokenType.ACCESS.name().equals(extractType(token));
    }
    public boolean isRefreshToken(String token) {
        return TokenType.REFRESH.name().equals(extractType(token));
    }
}
