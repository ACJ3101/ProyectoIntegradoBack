package com.miempresa.tienda_crochet.repository;

import com.miempresa.tienda_crochet.model.Rol;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
		   
        Optional<Rol> findByNombre(String nombre);  
}
