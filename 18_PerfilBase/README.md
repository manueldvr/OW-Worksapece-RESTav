# Profiles



Los perfiles de Spring permiten un mecanismo para para separar
determinadas partes en la configuración de un proyecto.

Podemos hacer que cada una de esas partes esté disponible en un
determinado entorno:<br>
+ Desarrollo (dev)
+ Testing (test)
+ Producción (prod)
+ ...


## @Profile
Anotación que puede acompañar a<br>
+ @Component (y sus derivados)
+ @Configuration
+ @ConfigurationProperties

● A través de ella indicamos el perfil o perfiles en los cuales
tendremos a nuestra disposición dicho componente

ejemplo:

```java
@Profile
@Configuration
@Profile("production")
public class ProductionConfiguration {
    @Bean
    public Datasource datasource() {
        // ...
    }
}
```

## Activación

Perfiles y properties:<br>
● La propiedad `spring.profiles.active` nos permite indicar el perfil (o
perfiles) activos.<br>
● La más sencilla durante desarrollo es en `application.properties`con: <br>
`spring.profiles.active=dev,mysqldb`

Especificarlo a través del `pom.xml`:<br>
```xml
<plugins>
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
            <profiles>
                <profile>dev</profile>
            </profiles>
        </configuration>
    </plugin>
...
</plugins>
```

A través de un parámetro de la JVM:<br>
`-Dspring.profiles.active=dev`<br>

O incluso de una variable de entorno (la forma depende del sistema
operativo):<br>
`SPRING_PROFILES_ACTIVE=dev`

## Construcción

Creamos un nuevo fichero por perfil. <br>
El nombre será:

>`application-{profile}.properties` 

Por ejemplo:
> application-dev.properties <br>
application-prod.properties


Usualmente en producción la gestion de la BD se realiza con otras herramientas, y entonces se deja en `auto` o `update`:

> `spring.jpa.hibernate.ddl-auto=auto`

También la property `.show-sql=false` en producción.

When it runs logs will show that the postgres dialect is used:

> `2023-09-18 10:39:57.590  INFO 99326 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.PostgreSQL10Dialect`


<br><br>


---


## Referencias
<br>

comandos:

`docker pull postgres`

`docker run --name postgresql1 -p 5432:5432 -e POSTGRES_PASSWORD=postgresql -d postgres`

`docker exec -ti postgresql1 psql -U postgres -W postgres`

si un puerto esta ocupado, buscar el proceso que lo retiene:
`sudo lsof -i :<PUERTO>`

luego:

`sudo kill -9 <PROCESS ID>`