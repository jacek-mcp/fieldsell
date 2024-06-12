package com.jacek.fieldsell.repository;

import com.jacek.fieldsell.models.AuthenticatedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AuthenticatedUser, Integer> {

    Optional<AuthenticatedUser> findByUsername(String username);

}
