package com.jacek.fieldsell.services;


import com.jacek.fieldsell.dtos.LoginResponseDTO;
import com.jacek.fieldsell.models.AuthenticatedUser;
import com.jacek.fieldsell.models.Role;
import com.jacek.fieldsell.repository.RoleRepository;
import com.jacek.fieldsell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public AuthenticatedUser registerUser(String username, String password) {

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository
                .findByAuthority("USER")
                .orElseThrow(() -> new NoSuchElementException("No value present"));

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository.save(new AuthenticatedUser(0, username, encodedPassword, authorities));
    }

    public LoginResponseDTO loginUser(String username, String password) {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username)
                    .orElseThrow(() -> new NoSuchElementException("username not found")), token);
    }
}
