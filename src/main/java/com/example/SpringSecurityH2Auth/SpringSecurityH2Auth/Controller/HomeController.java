package com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/student")
    public String studentDashboard() {
        return "<h1>Welcome, Student!</h1>";
    }

    @GetMapping("/teacher")
    public String teacherDashboard() {
        return "<h1>Welcome, Teacher!</h1>";
    }
}
