package com.mercado.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .sessionManagement(
                pSession ->
                    pSession.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                    )
            )
            .authorizeHttpRequests(
                pPath ->
                    pPath
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/**").permitAll()
                        .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        http.headers(
            headers -> headers.frameOptions(
                frameOptionsConfig -> {
                    frameOptionsConfig.disable();
                    frameOptionsConfig.sameOrigin();
            })
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager(
            User
                .withUsername("admin")
                .password(passwordEncoder().encode("senha"))
                .roles("ADMIN")
                .build()
        );
    }
}