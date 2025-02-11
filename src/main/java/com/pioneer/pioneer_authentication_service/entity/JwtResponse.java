package com.pioneer.pioneer_authentication_service.entity;

public class JwtResponse {
    String userName;
    private String jwtToken;

    public JwtResponse(String userName, String jwtToken) {
        this.userName = userName;
        this.jwtToken = jwtToken;
    }
}
