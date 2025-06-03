package com.miempresa.tienda_crochet.repository;

import com.miempresa.tienda_crochet.model.PublicacionBlog;
import com.miempresa.tienda_crochet.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionBlogRepository extends JpaRepository<PublicacionBlog, Long> {
	List<PublicacionBlog> findByAutor(Usuario autor);

}
