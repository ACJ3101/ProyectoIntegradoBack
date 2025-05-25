package com.miempresa.tienda_crochet.repository;

import com.miempresa.tienda_crochet.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}