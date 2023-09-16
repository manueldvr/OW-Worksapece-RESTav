# PostgresSQL con Docker

## Instalar Postgresql con Docker

● `docker pull postgres` <br>
● `docker run --name postgresql -p 5433:5433 -e POSTGRES_PASSWORD=postgresql -d postgres`<br>

> --name Nombre del contenedor<br>
 -p Conexión de un puerto externo con interno<br>
 -e Establecemos una variable de entorno<br>
 -d El contenedor corre en background<br>

Asi tenemos un container con nombre `postgresC17`.

## Cliente para Postgresql
Desde el terminal. Para conectarnos para ver la BD por dentro: <br>

`docker exec -ti postgresql psql -U postgres -W postgres`<br>

> postgresql1: Nombre del contenedor<br>
 psql: Cliente de Postgresql<br>
 -U postgres: Nombre de usuario<br>
 -W: nos solicitará contraseña<br>
 postgres: Nombre de la base de datos<br>





docker run --name postgresql -p 5433:5433 -e POSTGRES_PASSWORD=postgresql -d postgres
docker exec -ti postgresql psql -U postgres -W postgres


## Properties

> spring.datasource.url=jdbc:postgresql://localhost:5433/postgres<br>
spring.datasource.username=postgres<br>
spring.datasource.password=postgresql<br>
spring.jpa.show-sql=true<br>
spring.jpa.hibernate.ddl-auto=create-drop<br>
spring.datasource.initialization-mode=always<br>

