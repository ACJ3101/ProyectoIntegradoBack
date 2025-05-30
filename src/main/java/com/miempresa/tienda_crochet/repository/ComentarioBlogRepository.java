package com.miempresa.tienda_crochet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miempresa.tienda_crochet.model.ComentarioBlog;

public interface ComentarioBlogRepository extends JpaRepository<ComentarioBlog, Long> {
	List<ComentarioBlog> findByPublicacionId(Long publicacionId);

}

