package com.openwebinars.rest.model.repos;

import com.openwebinars.rest.model.CiudadPaisProj;
import com.openwebinars.rest.model.CiudadProj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.openwebinars.rest.model.Ciudad;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 *
 */
@RepositoryRestResource(
        path = "ciudades",
        collectionResourceRel = "ciudades",
        excerptProjection = CiudadPaisProj.class)
public interface CiudadRepositorio extends JpaRepository<Ciudad, Long>{

    @RestResource(path = "nombreComienzaPor",  rel="nombreComienzaPor")
    Page<Ciudad> findByNombreStartsWith(@Param("nombre") String nombre, Pageable pageable);
}
