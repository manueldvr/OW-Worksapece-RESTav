package com.openwebinars.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Se expondra un API para Paises y sus ciudades pertenecientes.
 *
 * Con una asociaciòn uni-direccional *Many-To-One*.
 */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Ciudad {

	@Id @GeneratedValue
	private Long id;
	
	private String nombre;

	private String lat;

	private String lng;

	@ManyToOne
	@JoinColumn(name = "pais_id")
	private Pais pais;
	
}
