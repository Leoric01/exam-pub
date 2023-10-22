package com.urban.exampub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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
                    .requestMatchers(HttpMethod.GET,"/summary/**").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET,"/users").hasAuthority("ROLE_USER")
                    .requestMatchers(HttpMethod.GET,"/users/**").hasAuthority("ROLE_USER")
                    .requestMatchers(HttpMethod.POST,"/user").permitAll()
                    .requestMatchers(HttpMethod.GET,"/drink-menu").permitAll()
                    .requestMatchers(HttpMethod.POST,"/buy").permitAll()
                    .requestMatchers(HttpMethod.POST,"/drink").hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.POST,"/create-buy").hasAuthority("ROLE_USER")
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                    // disallow everything else if we forgot to include some endpoint
                    .anyRequest().authenticated()
            )
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(Customizer.withDefaults())
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }
}
