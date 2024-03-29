package com.openwebinars.rest.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openwebinars.rest.model.Pais;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(exported = false)
public interface PaisRepositorio extends JpaRepository<Pais, Long>{

}
