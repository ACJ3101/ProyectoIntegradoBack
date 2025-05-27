package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.dto.ComentarioBlogDTO;
import com.miempresa.tienda_crochet.model.ComentarioBlog;
import com.miempresa.tienda_crochet.model.PublicacionBlog;
import com.miempresa.tienda_crochet.model.Usuario;
import com.miempresa.tienda_crochet.repository.ComentarioBlogRepository;
import com.miempresa.tienda_crochet.repository.PublicacionBlogRepository;
import com.miempresa.tienda_crochet.repository.UsuarioRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios-blog")
@CrossOrigin("*")
public class ComentarioBlogController {

    private final ComentarioBlogRepository comentarioBlogRepository;
    private final UsuarioRepository usuarioRepository;
    private final PublicacionBlogRepository publicacionBlogRepository;

    public ComentarioBlogController(
        ComentarioBlogRepository comentarioBlogRepository,
        UsuarioRepository usuarioRepository,
        PublicacionBlogRepository publicacionBlogRepository
    ) {
        this.comentarioBlogRepository = comentarioBlogRepository;
        this.usuarioRepository = usuarioRepository;
        this.publicacionBlogRepository = publicacionBlogRepository;
    }

    @PostMapping
    public ResponseEntity<ComentarioBlog> crearComentario(@RequestBody ComentarioBlogDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(dto.getUsuarioId());
        Optional<PublicacionBlog> publicacionOpt = publicacionBlogRepository.findById(dto.getPublicacionId());

        if (usuarioOpt.isEmpty() || publicacionOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ComentarioBlog comentario = new ComentarioBlog();
        comentario.setComentario(dto.getComentario());
        comentario.setCalificacion(dto.getCalificacion());
        comentario.setFecha(new Date());
        comentario.setUsuario(usuarioOpt.get());
        comentario.setPublicacion(publicacionOpt.get());

        return ResponseEntity.status(201).body(comentarioBlogRepository.save(comentario));
    }
}
