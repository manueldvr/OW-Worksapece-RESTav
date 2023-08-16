package com.openwebinars.rest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductoDTO {
	
	private long id;
	private String nombre;
	private String imagen;
	private String categoria;
	

}
