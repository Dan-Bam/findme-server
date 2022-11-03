package com.project.findme.global.security.jwt;

import com.project.findme.global.security.jwt.properties.JwtKeyProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtKeyProperties jwtKeyProperties;

    private static final Long ACCESS_TOKEN_EXPIRED_TIME = 1000 * 60 * 60 * 3L; // 3시간
    private static final Long REFRESH_TOKEN_EXPIRED_TIME = 14 * 24 * 60 * 60 * 1000L; // 2주

    @AllArgsConstructor
    enum TokenType {
        ACCESS_TOKEN("accessToken"),
        REFRESH_TOKEN("refreshToken");

        private final String value;
    }

    private Key getSignInKey(String secretKey) {
        byte keyByte[] = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException, IllegalStateException, UnsupportedOperationException {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey(jwtKeyProperties.getKey()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserId(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isExpired(String token) {
        try {
            extractAllClaims(token).getExpiration();
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private String doGenerateToken(String id, TokenType tokenType, Long expiredTime) {
        Claims claims = Jwts.claims().setSubject(id);
        claims.put("tokenType", tokenType.value);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date((System.currentTimeMillis())))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(getSignInKey(jwtKeyProperties.getKey()))
                .compact();
    }

    public String generateAccessToken(String id) {
        return doGenerateToken(id, TokenType.ACCESS_TOKEN, ACCESS_TOKEN_EXPIRED_TIME);
    }

    public String generateRefreshToken(String id) {
        return doGenerateToken(id, TokenType.REFRESH_TOKEN, REFRESH_TOKEN_EXPIRED_TIME);
    }

}
