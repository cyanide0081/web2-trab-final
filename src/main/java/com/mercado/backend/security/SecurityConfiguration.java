package com.mercado.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private SecurityFilter securityFilter;

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
                        .requestMatchers(HttpMethod.POST, "/auth/login")
                            .permitAll()
                        .requestMatchers(
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/h2-console/**",
                            "/error/**",
                            "/api/v1/user"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                        .anyRequest().authenticated()
            )
            .addFilterBefore(
                securityFilter,
                UsernamePasswordAuthenticationFilter.class
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
    public InMemoryUserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager(
            User
                .withUsername("user")
                .password(passwordEncoder().encode("senha"))
                .roles("USER")
                .build(),
            User
                .withUsername("admin")
                .password(passwordEncoder().encode("senha"))
                .roles("ADMIN")
                .build()
        );
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration conf
    ) throws Exception {
        return conf.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}