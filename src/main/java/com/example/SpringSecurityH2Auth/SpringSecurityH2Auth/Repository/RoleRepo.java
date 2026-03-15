package com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Repository;

import com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
