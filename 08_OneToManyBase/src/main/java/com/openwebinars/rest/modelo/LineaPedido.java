package com.openwebinars.rest.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Clase complementaria (componente) de Pedido.
 * Mapeada a linea_pedido.
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="linea_pedido")
public class LineaPedido {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Producto producto;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private float precio;

    private  int cantidad;

    public   float getSubtotal() {
        return precio*cantidad;
    }
}