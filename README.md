# Proyecto Quarkus con PostgreSQL

Este proyecto está desarrollado con **Quarkus**, un framework Java moderno, rápido y optimizado para microservicios.

## Tecnologías utilizadas

- Java 17+
- Quarkus 3
- Hibernate ORM con Panache
- RESTEasy (JAX-RS)
- PostgreSQL
- Docker
- Maven Wrapper

## Requisitos

- Java JDK 17 o superior
- Docker
- VS Code (opcional, recomendado)

## Levantar PostgreSQL con Docker

Ejecuta el siguiente comando:

```bash
docker run --name postgres-db -e POSTGRES_DB=matricula_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
