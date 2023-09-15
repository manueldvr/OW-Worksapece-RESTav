# HATEOAS y Spring Data REST

capitulos:
+ 11
+ 12
+ 13
+ 14

index:

+ ¿Qué es HATEOAS y HAL?
+ Spring Data REST
+ Primer ejemplo con Spring Data REST
+ Configuración de Parámetros
+ Paginación y Búsqueda
+ Proyecciones
+ Excerpts: Composición de Modelos

---

<br>

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

<br>

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

<br><br>

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
<br><br>

# Spring Data Rest

<br>

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

```


Podemos ir navegando entre enlaces para obtener el listado de
países o ciudades

Podemos paginar los resultados obtenidos.
● Nos da los enlaces de paginación habituales

Petición POST a la URL correspondiente


# Spring Data REST

si queremos implementar **HATEOAS (Hypermedia as the Engine of Application State)** en nuestro proyecto o 
si hay muchos criterios sobre los que debemos acceder a los datos, deberemos escribir 
bastante código. 
Y mas cantidad de código, implica mayor esfuerzo en el mantenimiento de nuestra aplicación. 

Para solucionar este problema Spring Boot provee el paquete **Spring Data Rest** con el 
cual con pocas lineas podremos crear una API para acceder a las tablas de nuestra base de 
datos.

agregar la dependecia en el `pom.xml`

```xml
<dependency>     
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```
Con esta dependecia ya podemos utilizar la propertie para configurar la ruta donde quedara expuesta el api:

`spring.data.rest.base-path=/api`

Luego, en el repositorio que queremos que este expuesto por el api, ponemos la siguiente anotacion:

```java
@RepositoryRestResource
        (path = "customers", collectionResourceRel = "customers")
```

Gracias a esta anotacion, Spring Data Rest puede:
+ exponer nuestro repositorio con metodos REST y 
+ nos permite acceder a las acciones default del `CrudRepository` como `findAll`, `findById`, `save`, `delete`, y 
+ tambien a las busquedas custom creadas por nosotros, como por ejemplo `"findByNombre"`.

El parametro **path** es como se va a llamar el path del recurso, en este caso seria `/api/customers`, y 

el parametro **collectionResourceRel** indica como se va a llamar 
la propiedad del JSON en el que venga la lista de nuestra entidad, 
en este caso `"customers"`.








---

#### Por ejemplo 

si para crear un nuevo cliente, hacemos un POST:

`> curl -s --request POST localhost:8080/api/customer -d '{"id": 1, "name":"nombre cliente 1","address":"direccion cliente 1","telephone":"telefono cliente 1", "secret": "no guardar"}' -H "Content-Type: application/json"`

obtendremos:

```json
{
    "name":"nombre cliente 1",
    "address":"direccion cliente 1",
    "telephone":"telefono cliente 1",
    "city":null,
    "_links":{
        "self":{
          "href":"http://localhost:8080/api/customer/1"
        },
        "customerEntity":{
          "href":"http://localhost:8080/api/customer/1"
        }
    }
}
```

Como ven, nos retorna el cliente creado.

Si queremos ver la lista de clientes, hacemos un GET:

`> curl -s localhost:8080/api/customer`

```json
{
    "_embedded":{
        "customers":[
            {
            "name":"nombre cliente 1",
            "address":"direccion cliente 1",
            "telephone":"telefono cliente 1",
            "city":{
                "name":"Logroño",
                "province":"La Rioja"
                },
                "_links":{
                    "self":{
                      "href":"http://localhost:8080/api/customer/1"
                    },
                    "customerEntity":{
                      "href":"http://localhost:8080/api/customer/1"
                    }
                }
            }
        ]
        },
        "_links":{
            "self":{
            "href":"http://localhost:8080/api/customer"
        },
            "profile":{
            "href":"http://localhost:8080/api/profile/customer"
        },
        "search":{
            "href":"http://localhost:8080/api/customer/search"
        }
    }
}
```

---

## Configuraciones

+ URI base
+ mecanismo de detecciòn de repositorios
+ parámetros de paginación
+ formato de salida

### Cambio de la URI base
● Por defecto, Spring Data REST sirve recursos REST en el URI raíz /<br>
● Es muy habitual que nuestra API REST tenga como base en las rutas
el segmento `/api/`.<br>
● Podemos modificarlo a través de properties.<br>
```
spring.data.rest.basePath
spring.data.rest.base-path
```

## Estrategia de detección de repositorios
Spring Data REST utiliza `RepositoryDetectionStrategy` para
determinar la estrategia. Puede elegir entre estas (enumeración
`RepositoryDiscoveryStrategies`)

+ **DEFAULT**: expone todas las interfaces repositorios públicas,
pero teniendo en cuenta el flag exported de la anotaciones
@RepositoryRestResource` o `@RestResource`.
+ **ALL**: expone todas las interfaces repositorios,
independientemente de la visibilidad o las anotaciones.
+ **ANNOTATION**: expone solo los repositorios anotados con
`@RepositoryRestResource` o los de aquellas entidades con
`@RestResource`, siempre y cuando el flag exported **no** sea *false*.
+ **VISIBILITY**: expone solo los repositorios públicos anotados.

● Para configurarlo necesitamos un componente que implemente el
método `configureRepositoryRestConfiguration` de la interfaz
RepositoryRestConfigurer`.


### ejemplo de negar exportación de un repo:

para PaisRepository podemos hacer:

```java
@RepositoryRestResource(exported = false)
public interface PaisRepositorio extends JpaRepository<Pais, Long>{
}
```

Tiene prioridad `RepositoryDetectionStrategy` para
determinar la estrategia:
```java
@Component
public class RestConfiguration implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ALL);
    } ...
```

## Cambio de otros parámetros
● Tamaño de página por defecto: <br>
`spring.data.rest.default-page-size` <br>
● Tamaño de página máximo:<br>
`spring.data.rest.max-page-size`<br>
● Tipo de medio por defecto:<br>
`spring.data.rest.default-media-type`<br>
● Devolver o no el recurso recién creado en petición POST:<br>
`spring.data.rest.return-body-on-create`<br>

<br><br><br>

---

## Spring Data REST: Paginación y búsqueda

### Métodos de búsqueda
Spring Data REST también es capaz de exponer nuestras consultas
en el repositorio como *endpoints*.

Estas, además, también puede ser paginadas:

```java
@RestResource
        (path = "nombreComienzaPor", rel = "nombreComienzaPor")
public Page<Ciudad> findByNombreStartsWith(
    @Param("nombre") String nombre, Pageable p);
```

y se lo usa:

`http://localhost:8081/api/ciudades/search/nombreComienzaPor?nombre=M`

<br><br><br>

## Proyeccion

Por defecto, Spring Data REST exporta los objetos de dominio tal
cual están definidos.<br>
○ Los datos primitivos, se exportan tal cual. <br>
○ Para los datos que asocian con otro modelo:<br>
■ Si hay un repositorio exportado, como una URI (también objeto anidado embedded).<br>
■ Si no hay repositorio exportado, como un objeto anidado.

Entonces, nos puede interesar tener algunos endpoints en los cuales los datos
que se obtengan del objeto no sean todos, sino solamente unos
pocos.

● Por ejemplo, ciudad sin país ni coordenadas

### Implementación

Se definen a través de una interfaz.

Uso de la anotación `@Projection`:<br>
1. Se indica el nombre de la proyección con **name** y la clase sobre la que se
define con **types**.

2. **Un método getter por cada dato que queremos exportar**:

ejemplo:
```java
@Projection
        (name = "ciudadSinUbicacion", types = { Ciudad.class })
public interface CiudadProj {
    String getNombre();
}
```

### ¿Cómo se usa?

Si ejecutamos ahora la aplicación podemos consumir la url de una
ciudad. 

Por ejemplo http://localhost:8080/api/ciudades/29. <br>
Podemos ver que aparece una nueva entrada en el JSON.<br>

Hateoas nos indica la disponibilidad:<br>
`"href": "http://localhost:8081/api/ciudades/29{?projection}",`

y con eso se arma la invocación:<br>
`http://localhost:8081/api/ciudades/29?projection=ciudadSinUbicaction`

### Proyecciones más complejas

Podemos definir proyecciones más complejas.<br>

● Uso de `@Value` y `SpEL`<br>
● Podemos obtener datos de asociaciones o construir datos
derivados.<br>
● Por ejemplo, incluir el nombre del país, no el objeto completo.<br>
● Incluir también las coordenadas en formato *lat*, *lng*.<br>

<br><br><br>

---

## Excerpts

Se trata de una proyección que queremos usar en lugar del modelo
habitual.

● Se aplica automáticamente a un recurso de tipo colección.<br>
● Suele ser habitual obtener estructura de objetos diferentes cuando
obtenemos un listado de una entidad, y cuando obtenemos una sola
instancia de la entidad (en el listado, normalmente, no obtenemos
tantos datos).<br>

### Cómo usar?

● La anotación `@RepositoryRestResource` incluye la propiedad
**excerptProjection**, que nos permite indicar una proyección para usar
como excerpt de este repositorio:
```java
@RepositoryRestResource(path = "ciudades", collectionResourceRel = "ciudades", excerptProjection = CiudadProj.class)
public interface CiudadRepositorio extends JpaRepository<Ciudad, Long>{
    @RestResource(path = "nombreComienzaPor", rel = "nombreComienzaPor")
    public Page<Ciudad> findByNombreStartsWith (@Param("nombre") String nombre, Pageable p);
}
```

El resultado utiliza para este repo la proyección que muestra la información de modelo me forma reducida.
Mientras que usando otra proyección puede mostrar todo.


### Composición de objetos
● Este es un buen momento para pensar en un buen diseño de
nuestras proyecciones.<br>

● Si nuestra clase modelo incluye asociaciones, es posible que en la
proyección que vamos a usar en los recursos de tipo colección
necesitemos algunos de los datos asociados.<br>

● Es posible **componer un objeto con los datos asociados** para que la
respuesta que obtenga el cliente sea algo más completa.<br>

**Nota:**
> Excerpt projections are not automatically applied to single resources. They have to be applied deliberately. Excerpt projections are meant to provide a default preview of collection data but not when fetching individual resources. See Why is an excerpt projection not applied automatically for a Spring Data REST item resource? for a discussion on the subject.

**Warning**
> Configuring @RepositoryRestResource(excerptProjection=…​) for a repository alters the default behavior. This can potentially cause breaking changes to consumers of your service if you have already made a release.

<br><br><br>

---

## Referencias

Guia General [Spring Data REST Reference Guide](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#intro-chapter)

[Spring Data REST](https://somospnt.com/blog/101-springdatarest)

https://github.com/spring-projects/spring-hateoas-examples

En la [documentación oficial](https://docs.spring.io/springdata/rest/docs/3.1.10.RELEASE/reference/html/#gettingstarted.changing-other-properties) está la lista completa de
propiedades.