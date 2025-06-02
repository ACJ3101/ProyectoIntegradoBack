package com.miempresa.tienda_crochet.controller;

import com.miempresa.tienda_crochet.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pago")
@CrossOrigin("*")
public class PagoController {

    private final StripeService stripeService;

    public PagoController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @Value("${stripe.public.key}")
    private String publicKey;

    @PostMapping("/crear-sesion")
    public ResponseEntity<Map<String, String>> crearSesion(
            @RequestParam Double monto,
            @RequestParam String nombreProducto) {

        try {
            // Convierte euros a centavos
            long montoCentavos = Math.round(monto * 100);

            // Crear sesión de pago a través del servicio
            Session session = stripeService.crearSesionPago(
                    montoCentavos,
                    "eur",
                    nombreProducto
            );

            Map<String, String> response = new HashMap<>();
            response.put("sessionId", session.getId());
            response.put("url", session.getUrl());
            return ResponseEntity.ok(response);

        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/public-key")
    public ResponseEntity<String> obtenerClavePublica() {
        return ResponseEntity.ok(publicKey);
    }
    
    @GetMapping("/verificar/{sessionId}")
    public ResponseEntity<String> verificarEstadoPago(@PathVariable String sessionId) {
        try {
            Session session = Session.retrieve(sessionId);
            if ("complete".equals(session.getStatus())) {
                return ResponseEntity.ok("Pago completado correctamente");
            } else {
                return ResponseEntity.ok("Pago no completado. Estado: " + session.getStatus());
            }
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al verificar el pago: " + e.getMessage());
        }
    }

}
