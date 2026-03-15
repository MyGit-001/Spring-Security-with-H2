package com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Config;

import com.example.SpringSecurityH2Auth.SpringSecurityH2Auth.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((req) -> req
                        .requestMatchers("/student").hasRole("STUD")
                        .requestMatchers("/teacher").hasRole("TEACH")
                        .anyRequest().authenticated())
                .formLogin(form -> form.successHandler(authenticationSuccessHandler()))
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return (request, response, authentication) ->
        {
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUD")))
            {
                response.sendRedirect("/student");
            }
            else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_TEACH")))
            {
                response.sendRedirect("/teacher");
            }
        };
    }
}
