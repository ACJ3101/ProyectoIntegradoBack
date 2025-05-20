package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.model.Usuario;
import com.miempresa.tienda_crochet.repository.UsuarioRepository;
import com.miempresa.tienda_crochet.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.email());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        if (!passwordEncoder.matches(request.password(), usuario.getContraseña())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        String accessToken = jwtUtil.generateAccessToken(usuario.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(usuario.getEmail());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    public record LoginRequest(String email, String password) {}
    public record AuthResponse(String accessToken, String refreshToken) {}

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody TokenRequest request) {
        if (jwtUtil.validateToken(request.refreshToken())) {
            String username = jwtUtil.extractUsername(request.refreshToken());
            String newAccessToken = jwtUtil.generateAccessToken(username);
            String newRefreshToken = jwtUtil.generateRefreshToken(username);
            return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken));
        }
        return ResponseEntity.status(403).build();
    }

    public record TokenRequest(String refreshToken) {}
}
