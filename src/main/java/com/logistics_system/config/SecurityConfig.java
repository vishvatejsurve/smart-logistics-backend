package com.logistics_system.config;

import com.logistics_system.security.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        http    .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth-> auth

                        .requestMatchers("/auth/**").permitAll()

                        .requestMatchers("/drivers/**").hasRole("ADMIN")
                        .requestMatchers("/orders/**").hasRole("ADMIN")

                        .requestMatchers("/orders/*/picked").hasRole("DRIVER")
                        .requestMatchers("/orders/*/delivered").hasRole("DRIVER")

                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
