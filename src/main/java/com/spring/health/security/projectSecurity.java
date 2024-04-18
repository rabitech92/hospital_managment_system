package com.spring.health.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class projectSecurity {

    public SecurityFilterChain filterChain (HttpSecurity http)throws Exception{
        http
                .authorizeHttpRequests((authorize)->authorize
                        .requestMatchers("/api").authenticated()
                )
                .authorizeHttpRequests((authorize)->authorize
                        .requestMatchers("/public/**").permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();

    }

}
