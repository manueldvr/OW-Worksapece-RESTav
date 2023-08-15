# Manejo de Parametros de Consultas - *parte II*
 
Cuando debemos buscar en base a varios campos que se pueden combinar entre sí, la cantidad de posibilidades se multiplica exponencialmente.

Manejo complicado en el Controlador
- ¿qué parámetros sí hemos recibido?¿cuáles no?

Consultas complejas en el repositorio
- muy dificil con consultas con nombres derivados
- también complicado con `@Query` o consultas nativas

## `Specification` y `JpaSpecificationExecutor`

`Specification`
- Mecanismo que nos permite predicados reutilizables para utilizar con `CriteriaQuery`.
- Los predicados pueden ser todo lo complejos que necesitamos.

```
public interface Specification<T> {
    Predicate toPredicat(Root<T>  root, CriteriaQuery query, CriteriaBuilder criteria);
}
```

`JpaSpecificationExecutor`

Un repositorio


---

# Ref

1. []().