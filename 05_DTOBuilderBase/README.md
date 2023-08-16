# *Modelo de Datos Complejos*

# Patron DTO en peticiones y respuestas
 **ver Patron DTO**






---

## Patron DTO

### 1ro definición de Entidad

Si son de JPA version 2.0: <br>
Requisitos:

- Constructor sin argumento y puede tener otros constructores
- El constructor sin atgs. puede ser público o protegido.
- Debe tener una clase de superior, aunque sea Object, no puede ser Enumeraciòn o Interfaz.
- No debería ser final, ni serlo ningún metodo o variable de instancia persistente de dicha clase.
- Si una instancia de la entidad se va a pasar por valor, por ejemplo a través de una interfaz remota, debería ser serializable.
- Pueden ser clases abstractas o concretas.
- Pueden extender clases que no son entidades.

Su objetivo es modelar la lógica de la capa de negocio.

### DTO : Data Transfer Object

Objeto POJO que agrupa datos de la capa de negocio y sirve para transferir datos entre diferentes capas:

- Objeto Plano -POJO-. Nada de lógica del negocio.
- Getters, Setters o constructores necesarios.
- Serializable.
- Puede tener parte de los datos de una sola entidad.
- Puede tener algunos datos de más de una entidad.
- Puede aglutinar todos los datos de varias entidades.


También conocido como **Value Object**.

Pensado para *aligerar* las transacciones entre cliente/servidor.<br>

ejemplo: un DTO podrìa ser la construcciòn de una clase ProductoDTO en base a a la unión de los datos de Producto y Categoría.

#### Cómo y dónde usarlos

Suponiendo que tenemos un catálogo de productos como el de nuestro ejemplo:
- GET /producto/ <br>
  EL listado de todos los productos puede ser orientado a ser visualizado en una paguina con todos los productos (on un subconjnto si x ejemplo hay paginaión). Y no necesariamiente todos los campos del objeto Producto, tal vez solo el nombre.
- GET /producto/{id} <br>
  En este caso es un producto individual. No necesariamente el DTO anterior. Una alternativa es usar la Entidad, otra es crear un DTO específico.

#### Multiples DTOs para un solo BO -Business Object-

Es posible tener más de un DTO para un solo OB.

Ejemplo es el *Usuario*, según la capa puede ser:
- UserEntity para la capa de *Persistencia*
- User para la capa de *capa de Seguridad*
- CreateUserDTO para la *petición de creación*
- GetUserDTO para la *solicitud de datos*


#### Implementación de DTOs

**Manualmente**
- con un builder (lombok)

**ModelMapper**

**JsonViews**
- A través de anotaciones, un mimso objeto puede devolver más o menos campos.




#### Value Object - VO vs DTO

A value object is a simple object whose equality isn't based on identity. <br>
A DTO is an object used to transfer data between software application subsystems, usually between business layers and UI. It is focused just on plain data, so it doesn't have any behaviour.

VO does not have to map directly against a domain entity, rather than to some fields of it or a diferent "picture" of it.


## Cómo transformar BO <--> DTO

Puede ser manualmente.

Con *ModelMapper*
- Evita código repetitivo
- Facilita la creación de DTO mediante asignación dinámica.
- CoC y configurable.


---

# Ref

1. []().