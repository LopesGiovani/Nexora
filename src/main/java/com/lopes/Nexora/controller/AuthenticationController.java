package com.lopes.Nexora.controller;

import com.lopes.Nexora.dto.LoginRequestDTO;
import com.lopes.Nexora.dto.LoginResponseDTO;
import com.lopes.Nexora.dto.UserRegistrationDTO;
import com.lopes.Nexora.infrastructure.entity.User;
import com.lopes.Nexora.infrastructure.repository.UserRepository;
import com.lopes.Nexora.infrastructure.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticateManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = authenticateManager.authenticate(authenticationToken);
        var user = (User) authentication.getPrincipal();
        var tokenJWT = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(tokenJWT));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegistrationDTO dto) {
        if (userRepository.findByLogin(dto.login()) != null) {
            return ResponseEntity.status(409).build();
        }
        var passwordHash = passwordEncoder.encode(dto.password());
        var newUser = new User(dto.login(), passwordHash);
        userRepository.save(newUser);
        return ResponseEntity.status(201).build();
    }

}
