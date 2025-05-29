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
import java.util.List;
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
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNick(dto.getUsuarioNick());
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

    @GetMapping("/publicacion/{id}")
    public ResponseEntity<List<ComentarioBlogDTO>> obtenerPorPublicacion(@PathVariable Long id) {
        List<ComentarioBlog> comentarios = comentarioBlogRepository.findByPublicacionId(id);

        List<ComentarioBlogDTO> comentariosDTO = comentarios.stream().map(comentario -> {
            ComentarioBlogDTO dto = new ComentarioBlogDTO();
            dto.setComentario(comentario.getComentario());
            dto.setCalificacion(comentario.getCalificacion());
            dto.setUsuarioNick(comentario.getUsuario().getNick()); // Aquí se toma el nick
            dto.setPublicacionId(comentario.getPublicacion().getId());
            return dto;
        }).toList();

        return ResponseEntity.ok(comentariosDTO);
    }

    
    
}
