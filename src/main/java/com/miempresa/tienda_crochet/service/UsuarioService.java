package com.miempresa.tienda_crochet.service;

import com.miempresa.tienda_crochet.model.Rol;
import com.miempresa.tienda_crochet.model.Usuario;
import com.miempresa.tienda_crochet.repository.RolRepository;
import com.miempresa.tienda_crochet.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario guardar(Usuario usuario) {
        if (usuario.getRol() != null && usuario.getRol().getId() != null) {
            Rol rolCompleto = rolRepository.findById(usuario.getRol().getId()).orElse(null);
            usuario.setRol(rolCompleto);
        }

        // 👉 Aquí se encripta la contraseña antes de guardar
        String contraseñaHasheada = passwordEncoder.encode(usuario.getContraseña());
        usuario.setContraseña(contraseñaHasheada);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    public Optional<Usuario> obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    
}
