package com.jacek.fieldsell.controllers;

import com.jacek.fieldsell.dtos.RegistrationDTO;
import com.jacek.fieldsell.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody RegistrationDTO body) {
        authenticationService.registerUser(body.username(), body.password());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody RegistrationDTO body) {
        String jwt = authenticationService.loginUser(body.username(), body.password()).jwt();
        ResponseEntity.ok().build();
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", jwt);
        return new ResponseEntity<>(tokenMap, HttpStatus.OK);
    }
}
