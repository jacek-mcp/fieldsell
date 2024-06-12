package com.jacek.fieldsell.controllers;

import com.jacek.fieldsell.dtos.LoginResponseDTO;
import com.jacek.fieldsell.dtos.RegistrationDTO;
import com.jacek.fieldsell.models.AuthenticatedUser;
import com.jacek.fieldsell.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticatedUser registerUser(@RequestBody RegistrationDTO body) {
        System.out.println(body.password());
        System.out.println(body.username());
        return authenticationService.registerUser(body.username(), body.password());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.username(), body.password());
    }
}
