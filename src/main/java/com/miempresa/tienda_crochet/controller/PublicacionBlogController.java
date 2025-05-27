package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.dto.PublicacionBlogDTO;
import com.miempresa.tienda_crochet.model.PublicacionBlog;
import com.miempresa.tienda_crochet.model.Usuario;
import com.miempresa.tienda_crochet.repository.PublicacionBlogRepository;
import com.miempresa.tienda_crochet.repository.UsuarioRepository;
import com.miempresa.tienda_crochet.service.PublicacionBlogService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin("*")
public class PublicacionBlogController {

    private final PublicacionBlogService publicacionBlogService;
    private final UsuarioRepository usuarioRepository;
    private final PublicacionBlogRepository publicacionBlogRepository;

    public PublicacionBlogController(
            PublicacionBlogService publicacionBlogService,
            UsuarioRepository usuarioRepository,
            PublicacionBlogRepository publicacionBlogRepository
    ) {
        this.publicacionBlogService = publicacionBlogService;
        this.usuarioRepository = usuarioRepository;
        this.publicacionBlogRepository = publicacionBlogRepository;
    }

    @GetMapping
    public List<PublicacionBlog> listar() {
        return publicacionBlogService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionBlog> obtener(@PathVariable Long id) {
        return publicacionBlogService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PublicacionBlog> crearPublicacion(@RequestBody PublicacionBlogDTO dto) {
        Optional<Usuario> autorOpt = usuarioRepository.findById(dto.getAutorId());

        if (autorOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        PublicacionBlog publicacion = new PublicacionBlog();
        publicacion.setTitulo(dto.getTitulo());
        publicacion.setContenido(dto.getContenido());
        publicacion.setCategoria(dto.getCategoria());
        publicacion.setFecha(new Date());
        publicacion.setAutor(autorOpt.get());

        PublicacionBlog guardada = publicacionBlogRepository.save(publicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        publicacionBlogService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
