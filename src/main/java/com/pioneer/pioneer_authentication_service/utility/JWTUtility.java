package com.pioneer.pioneer_authentication_service.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JWTUtility {
    private static String jwtSecretKey = AuthenticationConstants.jwtSecretKey;
    private static long jwtExpirationTime = AuthenticationConstants.jwtExpirationTime;

    public static String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private static <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
    }

    public static boolean validateToken(String username,String token) {
        String userNameFromToken = getUserNameFromToken(token);
        return userNameFromToken.equals(username) && !isTokenExpired(token);
    }

    private static boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public static String generateToken(String username) {
        System.out.println("jwtExpirationTime"+jwtExpirationTime+jwtSecretKey);
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationTime * 3600 * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
    }

}
