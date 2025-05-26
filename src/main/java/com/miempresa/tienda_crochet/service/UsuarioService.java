package com.miempresa.tienda_crochet.service;

import com.miempresa.tienda_crochet.dto.UsuarioUpdateDTO;
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

    public Optional<Usuario> actualizarUsuario(Long id, UsuarioUpdateDTO dto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()) return Optional.empty();

        Usuario usuario = optionalUsuario.get();

        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEmail(dto.getEmail());
        usuario.setDireccion(dto.getDireccion());
        usuario.setNick(dto.getNick());

        if (dto.getRolId() != null) {
            rolRepository.findById(dto.getRolId()).ifPresent(usuario::setRol);
        }

        return Optional.of(usuarioRepository.save(usuario));
    }
    
    public Optional<String> actualizarContrasena(Long id, String nuevaContrasena) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Verificar si la nueva contraseña es igual a la actual
            if (passwordEncoder.matches(nuevaContrasena, usuario.getContraseña())) {
                return Optional.of("La nueva contraseña no puede ser igual a la actual.");
            }

            // Encriptar y guardar
            usuario.setContraseña(passwordEncoder.encode(nuevaContrasena));
            usuarioRepository.save(usuario);
            return Optional.of("Contraseña actualizada correctamente.");
        }

        return Optional.of("Usuario no encontrado.");
    }



    
}
