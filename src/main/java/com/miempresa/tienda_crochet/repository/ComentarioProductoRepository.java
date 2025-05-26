package com.miempresa.tienda_crochet.repository;

import com.miempresa.tienda_crochet.model.ComentarioProducto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioProductoRepository extends JpaRepository<ComentarioProducto, Long> {
	
	List<ComentarioProducto> findByProductoId(Long productoId);

}
