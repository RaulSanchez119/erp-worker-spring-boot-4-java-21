# erp-worker

Worker service for [erp-lite](https://github.com/RaulSanchez119/erp-lite-spring-boot-4-java-21), handling batch processing tasks that shouldn't run on the API's main thread.

## Qué hace

Ejecuta un job programado (Spring Batch) que:
- Lee productos y catálogos desde MongoDB
- Los transforma a DTOs optimizados para caché
- Los guarda en Redis con TTL, para que `erp-lite` los sirva rápido sin golpear Mongo en cada petición

## Stack

- Spring Boot
- Spring Batch
- MongoDB (origen de datos)
- Redis (destino de caché)
- H2 (metadatos internos de Spring Batch)
