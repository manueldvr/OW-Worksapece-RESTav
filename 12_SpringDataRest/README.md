# HATEOAS y Spring Data REST

index:

+ ¿Qué es HATEOAS y HAL?
+ Spring Data REST
+ Primer ejemplo con Spring Data REST

---



# HATEOAS

**Hypermedia As The Engine Of Application State** → Hipermedia como el
motor del estado de la aplicación.

● No es algo nuevo (idea de REST de Roy Fielding).<br>
●  ver: Richardson Maturity Model.


● Dado un punto de entrada genérico en la aplicación, podemos ser
capaces de descubrir recursos.<br>
● La información devuelta por el servidor siempre incluye
hipervínculos:<br>
```json
{
    "links": [{
        "rel": "self",
        "href": "http://localhost:8080/api/v1/person/luismi"
    }],
    "name": "luismi",
    "age": 37
}
```
## HAL
HAL: **Hypertext Application Language**<br>

● En ocasiones, se mezclan los conceptos de HAL y HATEOAS<br>
● Estándar para definir hipermedia como enlaces a recursos externos
dentro de código XML o JSON.<br>
● Tipos mime: `application/hal+json` y `application/hal+xml`<br>
● Las APIs que adoptan HAL como formato pueden parecer más
complejas, pero en la práctica son muy útiles.<br>



## HATEOAS + HAL
● Como desarrolladores de API debemos pensar nuestros usuarios
finales son programadores (normalmente, de front-end). <br>
● ¿Por qué no hacerles la vida más fácil?

```json
{
    "self": "/books/spring/1234",
    "name": "HATEOAS para principiantes",
    "category": "/books/rest",
    "author" : {
        "name": "Luismi López",
        "self": "/authors/luismi-lopez"
    }
}
```

## Soporte de Spring

### Proyecto Spring HATEOAS

+ Facilita la creación de representaciones REST que siguen el
principio HATEOAS. 
+ Se centra en la creación de enlaces y el ensamblaje de
representación.
+ Varios ejemplos muy documentados
https://github.com/spring-projects/spring-hateoas-examples


### Spring HAL Browser/Explorer
+ Nos proporciona un aplicación web para cruzar nuestra api
REST.
+ Forma parte del proyecto **Spring Data REST**.
+ Lo podremos ver en próximas lecciones.




### Spring Data REST
+ Es la suma de **Spring Data + Spring HATEOAS + HAL**
+ Permite exponer, de forma muy sencilla, repositorios de Spring
Data como si fuera un API Rest que sigue los principios
HATEOAS y en formato HAL.
+ Lo aprenderemos a usar en las próximas lecciones.

---


# Spring Data Rest


**Spring Data REST** es parte del proyecto general **Spring Data** y
facilita la creación de servicios web REST.<br>

**Spring Data REST se basa en los repositorios de Spring Data**, analiza
el modelo de dominio de nuestra aplicación y expone los recursos
HTTP controlados por hipermedia (HATEOAS).

## Funcionalidades
● Expone una API REST reconocible (**discoverable**) para nuestro
modelo de dominio utilizando HAL.<br>
● Expone recursos de tipo item, colección y asociación que
representan a nuestro modelo.<br>
● Soporta paginación a través de links navegacionales.<br>
● Soporte para filtrado de colecciones.<br>
● Expone mecanismos de búsqueda para métodos de consulta
definidos en nuestros repositorios.<br>

● Podemos engancharnos (**hook**) en el manejo de peticiones REST y
otros eventos a través del manejo de ApplicationEvents de Spring.<br>
● Expone metadatos sobre el modelo como un esquema JSON o **ALPS**<br>
● Permite definir representaciones específicas del modelo a través de
**Proyecciones**.<br>
● Envía una variante personalizada del **HAL browser** para aprovechar
los metadatos expuestos.<br>

● Permite personalizaciones avanzadas de los recursos
predeterminados expuestos.<br>
● Actualmente (versión 3.2.0) soporta JPA, MongoDB, Neo4j, Solr,
Cassandra y Gemfire.

---

# Proyecto con Spring Data REST

+ Spring Starter Project
+ Incluimos las dependencias usuales<br>
○ Web, Data Jpa, H2, Lombok<br>
+ y añadimos:<br>
○ Rest Repositories<br>
○ Rest Repositories HAL Browser<br>


## Entidades

Se expondra un API para Paises y sus ciudades pertenecientes.

Con una asociaciòn uni-direccional *Many-To-One*.

#### Modelos

```java
@Data @NoArgsConstructor
@AllArgsConstructor @Builder
@Entity
public class Pais {
    @Id @GeneratedValue
    private Long id;
    private String
    nombre;
}
```

```java
@Data @NoArgsConstructor
@AllArgsConstructor @Builder
@Entity
public class Ciudad {
    @Id @GeneratedValue
    private Long id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;
}
```

#### Repositorios

● Creamos los repositorios para País y Ciudad.<br>
● En el de Ciudad, añadimos @RepositoryRestResource para
customizar algunas propiedades<br>
● Añadimos algunos datos iniciales.<br>

#### ejecución

Si consumimos de la raíz del API, tenemos una descripción de la
misma en formato HAL.

```json
{
    "_embedded": {
        "ciudades": [
            {
                "nombre": "Sevilla",
                "_embedded": {
                    "pais": {
                        "nombre": "España"
                    }
                },
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/ciudades/1"
                    },
                    "ciudad": {
                        "href": "http://localhost:8081/ciudades/1"
                    },
                    "pais": {
                        "href": "http://localhost:8081/ciudades/1/pais"
                    }
                }
            },
            {
                "nombre": "Madrid",
...
```


Podemos ir navegando entre enlaces para obtener el listado de
países o ciudades

Podemos paginar los resultados obtenidos.
● Nos da los enlaces de paginación habituales

Petición POST a la URL correspondiente






## Referencias

https://github.com/spring-projects/spring-hateoas-examples
