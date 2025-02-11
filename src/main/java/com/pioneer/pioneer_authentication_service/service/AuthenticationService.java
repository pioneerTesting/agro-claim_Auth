package com.pioneer.pioneer_authentication_service.service;

import com.pioneer.pioneer_authentication_service.entity.JwtRequest;
import com.pioneer.pioneer_authentication_service.utility.JWTUtility;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {
    public HashMap<String,String> createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        //String userPassword = jwtRequest.getUserPassword();
        String newGeneratedToken = JWTUtility.generateToken(userName);
        HashMap<String,String> returnMap = new HashMap<String,String> ();
        returnMap.put("username",userName);
        returnMap.put("newGeneratedToken",newGeneratedToken);
        returnMap.put("info","authenticated");
        return returnMap;
    }
}
