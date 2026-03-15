package com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Repository;

import com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
