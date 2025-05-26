package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.dto.CambioContrasenaDTO;
import com.miempresa.tienda_crochet.dto.UsuarioDTO;
import com.miempresa.tienda_crochet.dto.UsuarioUpdateDTO;
import com.miempresa.tienda_crochet.mapper.UsuarioMapper;
import com.miempresa.tienda_crochet.model.Usuario;
import com.miempresa.tienda_crochet.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioService.listarTodos()
                .stream()
                .map(UsuarioMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtener(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(usuario -> ResponseEntity.ok(UsuarioMapper.toDto(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crearUsuario")
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(usuarioService.guardar(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/buscarPorEmail")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@RequestParam String email) {
        return usuarioService.obtenerPorEmail(email)
                .map(usuario -> ResponseEntity.ok(UsuarioMapper.toDto(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> getUsuarioAutenticado(Authentication authentication) {
        String email = authentication.getName();
        return usuarioService.obtenerPorEmail(email)
                .map(usuario -> ResponseEntity.ok(UsuarioMapper.toDto(usuario)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioUpdateDTO dto) {

        return usuarioService.actualizarUsuario(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}/cambiarContrasena")
    public ResponseEntity<String> cambiarContrasena(
            @PathVariable Long id,
            @RequestBody String nuevaContrasena) {

        Optional<String> resultado = usuarioService.actualizarContrasena(id, nuevaContrasena);

        if (resultado.isPresent()) {
            String mensaje = resultado.get();
            if (mensaje.equals("Contraseña actualizada correctamente.")) {
                return ResponseEntity.ok(mensaje);
            } else {
                return ResponseEntity.badRequest().body(mensaje);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }




}
