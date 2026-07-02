# erp-worker

Servicio worker complementario a **erp-lite**, encargado de tareas de procesamiento por lotes (batch) que no deben ejecutarse en el hilo principal de la API.

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
