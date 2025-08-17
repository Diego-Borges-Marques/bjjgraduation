package com.jiujitsu.graduation.controller;

import com.jiujitsu.graduation.domain.AuthenticationDTO;
import com.jiujitsu.graduation.domain.LoginResponseDTO;
import com.jiujitsu.graduation.domain.Usuario;
import com.jiujitsu.graduation.infra.security.TokenService;
import com.jiujitsu.graduation.repository.IUsuarioRepository;
import com.jiujitsu.graduation.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jiujitsu/auth")
public class AuthenticantionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private IUsuarioRepository repository;

    @Autowired
    private TokenService service;

    @Autowired
    private AuthorizationService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){

        UserDetails userDetails = authService.loadUserByUsername(data.email());

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.manager.authenticate(usernamePassword);

        var token = service.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}


