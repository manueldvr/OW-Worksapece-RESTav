package com.openwebinars.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.openwebinars.rest.util.pagination.PaginationLiksUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openwebinars.rest.dto.CreateProductoDTO;
import com.openwebinars.rest.dto.ProductoDTO;
import com.openwebinars.rest.dto.converter.ProductoDTOConverter;
import com.openwebinars.rest.error.ProductoNotFoundException;
import com.openwebinars.rest.modelo.Producto;
import com.openwebinars.rest.service.ProductoServicio;

import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ProductoController {

	private final ProductoServicio productoServicio;

	private final ProductoDTOConverter productoDTOConverter;

	private final PaginationLiksUtils paginationLiksUtils;


	/**
	 * Obtenemos todos los productos
	 * 
	 * @return 404 si no hay productos, 200 y lista de productos si hay uno o más
	 */
	/*
	@GetMapping("/producto")
	public ResponseEntity<?> obtenerTodos() {
		List<Producto> result = productoServicio.findAll();

		if (result.isEmpty()) {
			throw new ProductoNotFoundException();
		} else {

			List<ProductoDTO> dtoList = result.stream().map(productoDTOConverter::convertToDto)
					.collect(Collectors.toList());

			return ResponseEntity.ok(dtoList);
		}

	}*/

	/**
	 * Obtenemos todos los productos paginados.
	 * Ahora findAll en vez de retornar un listado, retorna un Page de Producto.
	 * La clase Page define un metodo map, con lo cual no es necesario convertir a stream para hacer aplicar el converter.
	 * Luego se agrega la cabecera link.
	 *
	 * @see Page
	 * @param pageable Este se pasa a findAll. En caso de no recivir informacion, se define un param por defecto.
	 * @param request para obtener el uri de la peticion.
	 * @return 404 si no hay productos, 200 y lista de productos si hay uno o más
	 */
	@GetMapping("/producto")
	public ResponseEntity<?> obtenerTodos(@PageableDefault(size=10, page=0) Pageable pageable, HttpServletRequest request) {
		Page<Producto> result = productoServicio.findAll(pageable);

		if (result.isEmpty()) {
			throw new ProductoNotFoundException();
		} else {
			Page<ProductoDTO> dtoList = result.map(productoDTOConverter::convertToDto);
			UriComponentsBuilder uriBuilder
					= UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
			return ResponseEntity.ok().header("link",
					paginationLiksUtils.createLinkHeader(dtoList, uriBuilder)).body(dtoList);
		}

	}


	/**
	 * Obtenemos un producto en base a su ID
	 * 
	 * @param id
	 * @return 404 si no encuentra el producto, 200 y el producto si lo encuentra
	 */
	@GetMapping("/producto/{id}")
	public Producto obtenerUno(@PathVariable Long id) {

		return productoServicio.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));

	}

	/**
	 * Insertamos un nuevo producto
	 * 
	 * @param nuevo
	 * @return 201 y el producto insertado
	 */
	@PostMapping(value = "/producto", consumes= MediaType.MULTIPART_FORM_DATA_VALUE) //Aunque no es obligatorio, podemos indicar que se consume multipart/form-data
	public ResponseEntity<Producto> nuevoProducto(@RequestPart("nuevo") CreateProductoDTO nuevo, @RequestPart("file") MultipartFile file) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productoServicio.nuevoProducto(nuevo, file));
	}

	/**
	 * 
	 * @param editar
	 * @param id
	 * @return 200 Ok si la edición tiene éxito, 404 si no se encuentra el producto
	 */
	@PutMapping("/producto/{id}")
	public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id) {

		return productoServicio.findById(id).map(p -> {
			p.setNombre(editar.getNombre());
			p.setPrecio(editar.getPrecio());
			return productoServicio.save(p);
		}).orElseThrow(() -> new ProductoNotFoundException(id));

	}

	/**
	 * Borra un producto del catálogo en base a su id
	 * 
	 * @param id
	 * @return Código 204 sin contenido
	 */
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<Void> borrarProducto(@PathVariable Long id) {

		Producto producto = productoServicio.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));

		productoServicio.delete(producto);
		return ResponseEntity.noContent().build();

	}

}
