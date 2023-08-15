package com.openwebinars.rest.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.openwebinars.rest.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    /**
     * Consulta definida por derivación del nombre
     * Por la propiedad nombre de la clase Producto.
     * @param txt nombre
     * @param pageable
     * @return page of Product
     */
    Page<Producto> findByNombreContainsIgnoreCase(String txt, Pageable pageable);

    /**
     * Consulta definida por derivaciòn del nombre,  y precio menor a.
     * Por la propiedad nombre de la clase Producto.
     * @param txt nombre
     * @param pageable
     * @return page of Product
     */
    Page<Producto> findByNombreContainsIgnoreCaseAndPrecioLessThan(String txt, float precio, Pageable pageable);
}
