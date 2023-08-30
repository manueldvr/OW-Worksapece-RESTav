package com.openwebinars.rest.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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


}
