package com.openwebinars.rest.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.openwebinars.rest.modelo.Producto;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductoRepositorio extends JpaRepository<Producto, Long>,
		JpaSpecificationExecutor<Producto> {

	Page<Producto> findByNombreContainsIgnoreCase(String txt, Pageable pageable);

}
