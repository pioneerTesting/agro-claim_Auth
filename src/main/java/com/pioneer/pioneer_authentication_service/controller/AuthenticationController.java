package com.pioneer.pioneer_authentication_service.controller;

import com.pioneer.pioneer_authentication_service.entity.JwtRequest;
import com.pioneer.pioneer_authentication_service.service.AuthenticationService;
import com.pioneer.pioneer_authentication_service.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping({"/authenticateUser"})
    public HashMap<String,String> createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            return authenticationService.createJwtToken(jwtRequest);
        }
        catch (Exception e){
            HashMap<String,String> failedResponse=new HashMap<>();
            failedResponse.put("status","Failure");
            return failedResponse;
        }
    }

    @PostMapping({"/verifyToken"})
    public ResponseEntity<?> verifyToken(@RequestHeader(value="authorization") String token, @RequestParam(name = "userName") String userName) throws Exception {
        try {
            if (token.indexOf("Bearer ") == 0) {
                token = token.replaceAll("Bearer ", "");
            }
            if (JWTUtility.validateToken((String) userName, token)) {
                return ResponseEntity.status(HttpStatus.OK).body(true);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }

}
