package com.miempresa.tienda_crochet.service;

import com.miempresa.tienda_crochet.model.PublicacionBlog;
import com.miempresa.tienda_crochet.repository.PublicacionBlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionBlogService {

    private final PublicacionBlogRepository publicacionBlogRepository;

    public PublicacionBlogService(PublicacionBlogRepository publicacionBlogRepository) {
        this.publicacionBlogRepository = publicacionBlogRepository;
    }

    public List<PublicacionBlog> listarTodos() {
        return publicacionBlogRepository.findAll();
    }

    public Optional<PublicacionBlog> obtenerPorId(Long id) {
        return publicacionBlogRepository.findById(id);
    }

    public PublicacionBlog guardar(PublicacionBlog publicacionBlog) {
        return publicacionBlogRepository.save(publicacionBlog);
    }

    public void eliminar(Long id) {
        publicacionBlogRepository.deleteById(id);
    }
    
}
