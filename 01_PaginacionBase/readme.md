# Elementos Avanzados para API REST con SpringBoot

index
- Paginación de resultados



## Paginación de resultados

Cuando el resultado de una consulta es grande, es necesario dividrlo.
why?:

- Eficiencia: tiempo de procesamiento y carga muy grande.
- Experiencia de usuario.

## Qué ofrece Spring?

### Spring Data JPA

- Los repositorio nos ofrecen la posibilidad de realizar *consultas paginadas*.
- `JpaRepository<T,ID>` extiende a `PagingAndSortingRepository<T,ID>`, que tiene capacidades de paginación.
- Tenemos disponible el método `Page<T> findAll(Pageable pageable)`.
- Todas nuestras consultas pueden ser del tipo `Page<T> findByAlgo(Algo algo, Pageable pageable)`.

### `Pageable`

Es el elemento de entrada en una consulta paginada.

Interfaz con la información necesaria para extraer una *página* (un subconjunto) de resultados.

Métodos:

- `int getPageNumber()` número de la página.
- `int getPageSize()`tamaño de la página.
- `Sort getSort()` parámetros para ordener.

### `Page<T>`

Es el resultado de una consulta paginada.

Es una sublista de una lista de objetos.

Tiene información sobre la posición en la lista completa.

Extiende a la interfaz `Slice<T>`.

### `Page<T>` extends `Slice<T>`

Métodos:

- `List<T> getContent()` elementos en la página.
- `int getTotalPages()` número total de páginas.
- `int getTotalElements()` número total de elementos.
- `int getNumber()` número de pagina actual.
- `int getSize()` tamaño de la pagina actual.
- `int getNumberOfElements()` número de elementos en la página.


## Spring Data Web Support

La paginación debe ser soportada por la capa de persistencia subyacente. <br>
Las clases `Page` y `Pageable` pertenecen a Spring Data, no a Spring Web. <br>
Spring Boot configura automáticamente el soporte web para Spring Data (Spring Data Web Support).
Esto poermite usar `Pageable` en un controlador.

```java
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ProductController {
    ...

    @GetMapping("/producto")
    public String index(Model model, @PageableDefault(page=0,size=5) Pageable pageable){
        ...
    }
    ...
}
```

so call should be: *http://www.myapp.com/?page=1&size=10&sort=prop*

## Modelo para paginación

El resultado ya no será un `List<?>`.  <br>
Se deben dar facilidades a quien consuma del API para poder navegar a las demás páginas.  <br>
A falta de un modelo mejor usamos la propia interfaz `Page` (en concreto, la clase `PageImpl`).


## Paginación según RFC 5988

see: https://tools.ietf.org/html/rfc5988#section-5

Indica que en caso de paginación se debe añadir un encabezado llamado *link*.

Debe incluir los enlaces a las páginas primera, anterior, siguiente y última (siempre que aplique).

Tiene la estructura:

http://localhost:8080/producto/?page=2&size=10; rel="next" <br>
http://localhost:8080/producto/?page=0&size=10; rel="prev"<br>
http://localhost:8080/producto/?page=0&size=10; rel="first"<br>
http://localhost:8080/producto/?page=2&size=10; rel="last"<br>

