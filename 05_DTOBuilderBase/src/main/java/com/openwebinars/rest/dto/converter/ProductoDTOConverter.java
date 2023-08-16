package com.openwebinars.rest.dto.converter;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.openwebinars.rest.dto.ProductoDTO;
import com.openwebinars.rest.modelo.Producto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoDTOConverter {
	
	private final ModelMapper modelMapper;
	
	
	@PostConstruct
	public void init() {
		modelMapper.addMappings(new PropertyMap<Producto, ProductoDTO>() {
			@Override
			protected void configure() {
				map().setCategoria(source.getCategoria().getNombre());
			}
		});
	}

	/**
	 * Convert method with ModelMapper. Simple a primera vista.
	 * Pero modelMapper necesita saber cómo resolver para ocaciones especiales, como la relacion Producto-Categoria.
	 * Se en el método init().
	 * @param producto entity
	 * @return ProductoDTO DTO
	 */
	public ProductoDTO convertToDto(Producto producto) {
		return modelMapper.map(producto, ProductoDTO.class);
		
	}

	/**
	 * Convert method with Lombok. Es más largo. Pero fácil.
	 * @param producto entity
	 * @return ProductoDTO DTO
	 */
	public ProductoDTO convertProductoToProductoDTO(Producto producto) {
		return ProductoDTO.builder()
				.nombre(producto.getNombre())
				.imagen(producto.getImagen())
				.categoria(producto.getCategoria().getNombre())
				.id(producto.getId())
				.build();
	}
}
