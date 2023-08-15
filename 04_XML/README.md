# soporte XML
 

## Recurso VS Representacion

*Recurso* es una entidad, como Producto

*Representación* se refiere al formato:
- JSON
- XML
- HTML
- binario
- imagen
- String


## Negociación del Contenido

Al relizar una petición, se puede negociar el formato de la representación que:
- se envía
- se recibe

Encabezado;
- *Accept*: indica el tipode dato esperado de la respuesta
- *Content-Type*: indica el tipo de contenido del cuerpo de la petición.


## Formato por defecto

Spring Boot, al detectar Jackson2 en el classpath, formatea por defecto JSON.

Si se indica otro tipo de dato, como *application/xml*, obtendremos un error `406 Not Acceptable`.


## XML

Se necesita convertir las clases a XML y viceversa.

Alternativas:
- *JAXB*: obliga a añadir anotaciones adicionales a las de Jackson.
- **Jackson XML**: complemento para transformar a XML utilizando todo lo que se sabe de Jackson.


---

# Ref

1. []().