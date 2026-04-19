package com.example.assesment.security;

import com.example.assesment.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // CSRF = Cross Site Request Forgery
                // protection for browser FORMS
                // we are making REST API not forms!
                // so we disable it!
                //
                // Think of it like:
                // CSRF = protection for websites
                // JWT  = protection for APIs
                // we use JWT so CSRF not needed!

                .authorizeHttpRequests(auth -> auth
                                // start defining URL rules

                                .requestMatchers(
                                        "/auth/login",
                                        "/auth/register")
                                .permitAll()
                                //   no token needed!
                                //   open to everyone!

                                .requestMatchers("/users/delete/**")
                                // ** means any id
                                .hasAuthority("ROLE_ADMIN")
                                //   only ROLE_ADMIN can delete!
                                //   ROLE_USER tries → 403 FORBIDDEN!

                                .requestMatchers("/patients/**")
                                .hasAnyAuthority(
                                        "ROLE_ADMIN",
                                        "ROLE_USER")
                                //   both roles allowed!

                                .anyRequest().authenticated()
                        // everything else needs login
                )
                // ← authorizeHttpRequests CLOSES HERE!

                .sessionManagement(session -> session
                                // ← sessionManagement is OUTSIDE auth lambda!
                                .sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS)
                        // No session!
                        //
                        // Old way (STATEFUL):
                        // login → server creates session
                        //         remembers you in memory
                        //         gives you cookie
                        //
                        // Our way (STATELESS):
                        // login → get JWT token
                        //         server remembers NOTHING!
                        //         token has all info needed!
                        //         scales perfectly! ✅
                )

                .addFilterBefore(
                        jwtFilter,
                        // OUR custom filter
                        UsernamePasswordAuthenticationFilter.class);
        // Spring's default filter
        //
        // addFilterBefore means:
        // run OUR filter BEFORE Spring's filter
        //
        // Why before?
        // We need to set user in SecurityContext
        // BEFORE Spring checks authentication!

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // BCrypt = one way encryption
        //
        // "1234" → "$2a$10$xyz..." ✅
        // "$2a$10$xyz..." → ??? ❌ cannot reverse!
        //
        // During login:
        // BCrypt re-encrypts "1234"
        // compares with stored "$2a$10$xyz..."
        // match → login success ✅
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
        // Spring's built in manager
        // handles login verification!
        // we just expose it as a Bean
        // so AuthController can use it!
    }
}