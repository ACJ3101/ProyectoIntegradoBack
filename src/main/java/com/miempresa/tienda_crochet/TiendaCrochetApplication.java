package com.miempresa.tienda_crochet;

import com.miempresa.tienda_crochet.model.Rol;
import com.miempresa.tienda_crochet.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TiendaCrochetApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaCrochetApplication.class, args);
    }

    @Bean
    CommandLineRunner initRoles(RolRepository rolRepository) {
        return args -> {
            if (rolRepository.findAll().isEmpty()) {
                rolRepository.save(new Rol(null, "CLIENTE"));
                rolRepository.save(new Rol(null, "CREADOR"));
                rolRepository.save(new Rol(null, "ADMIN"));
            }
        };
    }
}
