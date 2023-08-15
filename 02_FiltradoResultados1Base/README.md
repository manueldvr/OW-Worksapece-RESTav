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


## Consultas Derivadas (derived queries)

Definen consultas para la clase de dominio que gestiona el repositorio usando el nombre del propio método. Con otras palabras, la consulta se «deriva» o deduce del nombre del método. Consultas fáciles de escribir, pero también de leer.

El nombre se compone de un prefijo que indica el tipo de consulta, 
- seguido de los criterios de selección de los resultados 
- y finalmente, con carácter opcional, la ordenación. 


Con `findByNameContainingOrderByNameAsc` se buscan los países (**find..By**) cuyo 
atributo nombre de la entidad (**Name**) contenga (**Containing**) cierta cadena 
(el parámetro del método), y que los resultados se ordenen (**OrderBy**) por el 
campo nombre (**Name**) de forma ascendente (**Asc**).

Los siguientes métodos son equivalentes:

```java
List<Country> findByNameContainingOrderByNameAsc(String name);
List<Country> readByNameContainingOrderByNameAsc(String name);
List<Country> getByNameContainingOrderByNameAsc(String name);
List<Country> queryByNameContainingOrderByNameAsc(String name);
List<Country> streamByNameContainingOrderByNameAsc(String name);
List<Country> searchByNameContainingOrderByNameAsc(String name);
```

Cuando Spring arranque y cree las implementaciones proxy de cada repositorio también generará una consulta **JPQL** para cada método.

Si no fuera posible, se lanzará una excepción de tipo `QueryCreationException` con un 
mensaje que circunstancia el problema. 
Por ejemplo, este método describe una consulta imposible:

```java
List<Country> findByNombreContaining(String capital);
```

El error explica que la clase Country no tiene un atributo llamado «nombre»:

```
org.springframework.data.repository.query.QueryCreationException: Could not create query for public abstract java.util.List com.danielme.springdatajpa.repository.query.CountryDerivedQueryRepository.findByNombreContaining(java.lang.String);
Reason: Failed to create query for method public abstract java.util.List com.danielme.springdatajpa.repository.query.CountryDerivedQueryRepository.findByNombreContaining(java.lang.String); No property 'nombre' found for type 'Country'
```

---

## Resumen:

- Las consultas derivadas se declaran con el nombre de un método en el repositorio. Esto se consigue combinando una serie de palabras claves: un prefijo, los criterios de selección de las entidades y la ordenación (opcional).
- Las variables de la consulta son parámetros del método que respetan el orden en el que aparecen en el nombre del método.
- Además de buscar entidades, puedes eliminarlas, contarlas y comprobar la existencia de algunas en particular.
- Existen diversas opciones para recoger el resultado de la consulta derivada. Las habituales son List y Optional.
- Los repositorios, al igual que cualquier bean de Spring, admiten la anotación @Async y el retorno de Future.

---

# Ref

1. Consultas derivadas [Curso Spring Data JPA. 5: consultas derivadas (derived queries)](https://danielme.com/2023/03/05/curso-spring-data-jpa-consultas_derivadas-derived_queries-repositorios_asincronos/)