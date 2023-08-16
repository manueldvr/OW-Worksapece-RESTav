# JSONView Transformations
 
Mecanismo que proporciona Jackson2 y Spring para seleccionar qué campos de un objeto serán transformados a JSON.

Este mecanismo permite tener, para un solo objeto Java diferentes representaciones en JSON.

Muy cómodo para diferenciar 2 vistas para un objeto:
- Conjunto de datos para un listado, una vista general,
- Vista de detalle cuando de lo accede.



## Funcionamiento

### Construcción

1. Definimos una clase que tendrá dentro varias interfaces
2. Serán las difrentes vistas que tendremos para una clase/entidad
3. Las interfaces pueden heredar unas de otras:

ie:
```java
public class ProductoViews {   // vistas del recurso Producto
    public interface Dto { }
    public interface DtoConPrecio extends Dto { }
}
```

4. En el modelo se usa @JonView sobre los diferentes atributos
5. Definmos, para cada uno, en qué vista o vistas lo queremos:

```java
public class ProductoDTO {
    @JsonView(ProductoViews.Dto.class) private String imagen;
    @JsonView(ProductoViews.DtoConPrecio.class) private float precio;
}
```

Es posible tener atributos que no tengan ningun @JsonView.
No serìan selectivos, se obtendran siempre.

### Como se usa

1. Anotamos a nivel de método, con la vista que queremos obtener


```java
@JsonView(ProductoViews.DtoConPrecio.class)
@GetMapping(value = "/producto")
public ResponseEntity<?> buscarProductosPorVarios(...) { ...
```

2. Añadir en `application.properties`:
`spring.jackson.mapper.default-view-inclusion=true`



## ¿Cuándo utilizarlo?

- Diferentes métodos en el controlador devuelven diferentes vistas de una misma clase (Maestro/Detalle).
- En función del ROL del usuario que hace la petición, queremos devolver más o menos campos.

ejemplo: Información de los Empleados de una Empresa
- ROL USER: información básica
- ROL ADMIN: info. más completa
- ROL HHRR: info sobre salario, cotizaciones, ...



   

---

# Ref

1. JSON Views: [Latest Jackson integration improvements in Spring](https://spring.io/blog/2014/12/02/latest-jackson-integration-improvements-in-spring).