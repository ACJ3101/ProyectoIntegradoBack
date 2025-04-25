package com.miempresa.tienda_crochet.repository;

import com.miempresa.tienda_crochet.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
