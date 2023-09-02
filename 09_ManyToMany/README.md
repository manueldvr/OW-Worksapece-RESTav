# Asociaciones


+ **Asociaciones Many-To-Many**


---

## Asociaciones Many-To-Many

● Una instancia de la entidad A se asocia con muchas instancias de
otra entidad B, y a la vez, una instancia de la entidad B, se asocia con
muchas instancias de A.<br>
● Ejemplo: la asociación *Autor ↔ Libro*: <br>
un autor puede escribir
muchos libros, y un libro puede estar escrito por más de un autor.<br>
● **Al igual que en one-to-many, es más eficiente el tratamiento
bidireccional de la asociación (a nivel JPA)**.<br>

## Bidireccionalidad idéntica a @OneToMany
● Recomendable para el tratamiento eficiente de la asociación.<br>
● Sin embargo, con Lombok/Jackson podemos tener problemas de
recursión infinita.<br>
● Para solucionarlo, podemos utilizar los mismos elementos.<br>

## @JsonBackReference / @JsonManagedReference
● Aunque la documentación indica que `@JsonBackReference` no
debería usarse sobre una colección, esta pareja de anotaciones
funciona en la práctica en el contexto de una asociación
`@ManyToMany` bidireccional.

## @JsonIdentityInfo
● Se trata de otra anotación que nos permite **solucionar el problema
de las referencias circulares en JSON**.<br>
● Se utiliza a nivel de objeto<br>
● A cada objeto JSON se le asigna un **identificador** (puede ser artificial
o una propiedad que ya tenga).<br>
● La 1ra vez que el objeto aparece en el JSON, aparece de forma
completa. La 2da vez, tan solo aparece el campo especial que
identifica a cada objeto (el ID).<br>

## ¿Mejor solución?
● Dependerá de cada contexto<br>
● Yo suelo preferir `@JsonManagedReference / @JsonBackReference`<br>
o la creación de Data Transfer Objects específicos.

## Implementación


### 1er solución con recursividad

Se crea como ejemplo con la clase `Lote`.

El fetch en la asociacion de Producto con eager trae todos los elementos, lo cual podrìa no ser necesario.

```java
...
@JsonIdentityInfo
        (generator = ObjectIdGenerators.PropertyGenerator.class
                , property = "id"
                , scope = Lote.class)
public class Lote {
	
	@Id @GeneratedValue
	private Long id;
	
	private String nombre;
	
	//@JsonManagedReference  (*)
	//@ManyToMany(fetch = FetchType.EAGER)
	@ManyToMany
	@JoinTable(
			joinColumns = @JoinColumn(name="lote_id"),
			inverseJoinColumns = @JoinColumn(name="producto_id")
	)
	@Builder.Default
	private Set<Producto> productos = new HashSet<>();
    ...	
```

Configuración en `Producto`. El fetch con eager trae todos los elementos. Entonces por defecto es Leasy, para que lo traiga cuando es necesario..
```java
...
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Producto.class )
public class Producto {

	@Id @GeneratedValue
	private Long id;
    
...
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	//@JsonBackReference (*)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
//	@ManyToMany(mappedBy="productos", fetch = FetchType.EAGER)
	@ManyToMany(mappedBy="productos")
	@Builder.Default
	private Set<Lote> lotes = new HashSet<>();
...	
}
```

Cuando se requiera traer productos en base al `id` de un Lote, se hace con una consulta en el repositorio.
```java
public interface LoteRepositorio extends JpaRepository<Lote, Long> {
	@Query("select l from Lote l JOIN FETCH l.productos WHERE l.id = :id")
	public Optional<Lote> findByIdJoinFetch(Long id);
}
```

### 2da solución sin recursividad

hay que descomentar  (*)
