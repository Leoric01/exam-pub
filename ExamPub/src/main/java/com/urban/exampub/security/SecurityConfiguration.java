package com.urban.exampub.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(
            authorizeHttpRequest -> authorizeHttpRequest
                    .requestMatchers(HttpMethod.GET,"/summary/**").hasAuthority("ROLE_admin")
                    .requestMatchers(HttpMethod.GET,"/users").permitAll()
                    .requestMatchers(HttpMethod.GET,"/users/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/user").permitAll()
                    .requestMatchers(HttpMethod.GET,"/drink-menu").permitAll()
                    .requestMatchers(HttpMethod.POST,"/buy").permitAll()
                    .requestMatchers(HttpMethod.POST,"/drink").permitAll()
                    .requestMatchers(HttpMethod.POST,"/create-buy").permitAll()
                    .requestMatchers(HttpMethod.POST, "/loign").permitAll()
                    // disallow everything else if we forget to define some endpoint
                    .anyRequest().authenticated()
            )
            .csrf(AbstractHttpConfigurer::disable)
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }
}
