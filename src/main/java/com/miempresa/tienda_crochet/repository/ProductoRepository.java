package com.miempresa.tienda_crochet.repository;

import com.miempresa.tienda_crochet.model.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	List<Producto> findByCategoriaId(Long categoriaId);
	
	List<Producto> findByUsuarioId(Long usuarioId);


}
