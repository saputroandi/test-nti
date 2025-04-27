package com.test.nti.services;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.test.nti.entities.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private final String SECRET_KEY = "e81ec632ef69309376a8ffe9113459797a956252ed0259dcac63c49fb5e9911d";

    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user) {

        String email = extractEmail(token);

        return (email.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {

        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims, T> resolver) {

        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(UserEntity user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() * 24 * 60 * 60 * 1000))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBites = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBites);
    }

}