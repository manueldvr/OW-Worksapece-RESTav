# Manejo de Parametros en el Query - *parte I*
 
Anteriormente las consultas han devuelto uno o todo los productos.

En el proyecto anterior se limita a la paginación.

Pero como:

- ... todos los productos de una categoría?
- ... todos los productos que contengan una cadena de caracteres?
- ... todos los productos cuyo precio sea menor a...?

## Estructura de una URL: query

Las URLs tienen, en su estructura, una parte denominada query (consulta).<br>
Viene a decirnos algo así que, sobre un determinado recurso, queremos establecer unas condiciones de filtrado.
- la parte final de la URL, y su comienzo viene determinado por un interrogante.
- a pertir de ahí, tenemos sucesivos parámetros *clave=valor*, separados por un ampresand, **&**.

## Uso de parámetros de un controlador

- con la anotación `@RequestParam` podemos tomar cualquier parámetro del controlador.
- estos parámetros pueden ser obligatorios u opcionales.
- se puede proporcionar un valor por defecto.
- **params** este método del controlador solo llama si incluye ese (o esos) parámetro/s.
- `@RequestParam`: inyecta el valor de la variable de tipo String:
```
@GetMapping(value= "/producto", params= "nombre")
public ResponseEntity<?> buscarProductosPorNombre(
    @RequestParam("nombre") String txt,
    Pageable pageable, HttpServletRequest request) {
        ...
    }
```
## Servicio y Repositorio

Si se va a buscar por nombre, necesitamos un método de consulta en el repositorio:
- `findByNombreContainsIgnoreCase(txt, pageable)`

Y en el servicio, se crea un wrapper:
- `findByNombre(String txt, Pageble pageable)` 