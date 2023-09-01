package com.openwebinars.rest.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Pedido {

    @Id
    @GeneratedValue
    private Long id;

    private String cliente;

    @CreatedDate
    private LocalDateTime fecha;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LineaPedido> lineas = new HashSet<>();

    public float getTotal(){
        return (float) lineas.stream()
                .mapToDouble(LineaPedido::getSubtotal)
                .sum();
    }

    /**
     * Add linea Pedido
     * metodos helper por la relación bidireccional
     */
    public void addLineaPedido(LineaPedido linea) {
        lineas.add(linea);
        linea.setPedido(this);
    }

    /**
     * Remove linea Pedido
     * metodos helper por la relación bidireccional
     */
    public void  removeLineaPedido(LineaPedido linea) {
        this.lineas.remove(linea);
        linea.setPedido(null);
    }
}
