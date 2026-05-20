package com.example.frankenstein.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private static final String SECRET = "minha-chave-secreta-super-segura-com-mais-de-32-caracteres";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarToken(String username, List<String> roles) {
        long agora = System.currentTimeMillis();

        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date(agora))
                .expiration(new Date(agora + 1000 * 60 * 60))
                .signWith(getSigningKey())
                .compact();
    }

    public String extrairUsername(String token) {
        return extrairClaims(token).getSubject();
    }

    public List<String> extrairRoles(String token) {
        return extrairClaims(token).get("roles", List.class);
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = extrairClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims extrairClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}