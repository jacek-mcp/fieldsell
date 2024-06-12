package com.jacek.fieldsell;

import com.jacek.fieldsell.models.AuthenticatedUser;
import com.jacek.fieldsell.models.Role;
import com.jacek.fieldsell.repository.RoleRepository;
import com.jacek.fieldsell.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class FieldSellDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FieldSellDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode) {
        return args -> roleRepository.findByAuthority("ADMIN").orElseGet(() -> {

                    final var adminRole = roleRepository.save(new Role("ADMIN"));
                    roleRepository.save(new Role("USER"));

                    final var roles = new HashSet<Role>();
                    roles.add(adminRole);

                    final var admin = new AuthenticatedUser(1, "admin", passwordEncode.encode("password"), roles);
                    userRepository.save(admin);

                    return adminRole;
                }
        );
    }

}
