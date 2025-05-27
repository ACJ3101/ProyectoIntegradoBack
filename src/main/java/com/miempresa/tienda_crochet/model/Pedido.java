package com.miempresa.tienda_crochet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "detalles")
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;
    private String estado;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario cliente;

    @ElementCollection
    @CollectionTable(name = "pedido_productos", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "producto_id")
    private List<Long> productoIds;
}
