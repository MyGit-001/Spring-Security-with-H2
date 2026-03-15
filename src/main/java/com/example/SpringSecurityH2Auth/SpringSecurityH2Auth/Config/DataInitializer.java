package com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Config;

import com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Entity.Role;
import com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Entity.User;
import com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Repository.RoleRepo;
import com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create roles if they don't exist
        Role studRole = roleRepo.findByName("ROLE_STUD").orElseGet(() -> roleRepo.save(new Role(null, "ROLE_STUD")));
        Role teachRole = roleRepo.findByName("ROLE_TEACH").orElseGet(() -> roleRepo.save(new Role(null, "ROLE_TEACH")));

        // Create users if they don't exist
        if (userRepo.findByUsername("stud").isEmpty()) {
            User student = new User();
            student.setUsername("stud");
            student.setPassword(passwordEncoder.encode("studPass"));
            student.setRoles(Set.of(studRole));
            userRepo.save(student);
        }

        if (userRepo.findByUsername("teach").isEmpty()) {
            User teacher = new User();
            teacher.setUsername("teach");
            teacher.setPassword(passwordEncoder.encode("teachPass"));
            teacher.setRoles(Set.of(teachRole));
            userRepo.save(teacher);
        }
    }
}
