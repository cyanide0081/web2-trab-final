package com.mercado.backend.controller;

import com.mercado.backend.model.AuthenticationDTO;
import com.mercado.backend.model.LoginResponseDTO;
import com.mercado.backend.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(
        @Valid @RequestBody AuthenticationDTO dto
    ) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()
            );

        Authentication auth = authenticationManager
            .authenticate(authenticationToken);
        String token = tokenService
            .generateToken((UserDetails)auth
            .getPrincipal());
        return ResponseEntity.ok().body(new LoginResponseDTO(token));
    }
}
