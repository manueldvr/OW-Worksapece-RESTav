# Postgres y Docker

comandos:

`docker pull postgres`

`docker run --name postgresql1 -p 5432:5432 -e POSTGRES_PASSWORD=postgresql -d postgres`

`docker exec -ti postgresql1 psql -U postgres -W postgres`

---

## Referencias

si un puerto esta ocupado, buscar el proceso que lo retiene:

`sudo lsof -i :<PUERTO>`

luego:

`sudo kill -9 <PROCESS ID>`