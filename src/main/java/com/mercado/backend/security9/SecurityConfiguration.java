//package com.mercado.backend.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfiguration {
////    @Bean
////    public InMemoryUserDetailsManager userDetailsManager() {
////        UserDetails vUser = User
////            .withUsername("mcardoso")
////            .password(passwordEncoder().encode("123abc"))
////            .roles("USER")
////            .build();
////
////        return new InMemoryUserDetailsManager(vUser);
////    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(AbstractHttpConfigurer::disable)
//            .cors(Customizer.withDefaults())
//            .sessionManagement(
//                session ->
//                    session.sessionCreationPolicy(
//                        SessionCreationPolicy.STATELESS
//                    ))
//            .authorizeHttpRequests(
//                path ->
//                    path
//                        .requestMatchers("/**").permitAll()
//                        .requestMatchers("/swagger-ui/**").permitAll()
//                        .requestMatchers("/v3/api-docs/**").permitAll()
//                        .anyRequest().authenticated()
//            )
//            .httpBasic(Customizer.withDefaults());
//
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
