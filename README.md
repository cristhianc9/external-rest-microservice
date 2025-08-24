# Microservicio Proxy con Quarkus

Microservicio reactivo y escalable construido con Quarkus 3, diseñado como un proxy adaptable para el consumo de servicios REST externos. Permite exponer endpoints propios que delegan la consulta a APIs de terceros, desacoplando la lógica de integración y facilitando la reutilización, escalabilidad y observabilidad.

## ¿Para qué sirve este microservicio?

Este proyecto actúa como un **proxy reactivo** para consumir servicios REST externos. Puedes adaptarlo fácilmente para integrarte con cualquier API, centralizando la lógica de consumo, transformación y exposición de datos a través de endpoints propios.

### Casos de uso típicos
- Integración con sistemas externos (APIs públicas, privadas, legacy, etc.).
- Centralización de lógica de consumo y transformación de datos.
- Exposición de endpoints internos desacoplados de proveedores externos.
- Facilidad para mockear y testear integraciones.

## Características principales

- **Arquitectura reactiva:** Basado en Mutiny y RESTEasy Reactive, soporta alta concurrencia y baja latencia.
- **Escalabilidad:** Listo para contenedores y orquestadores (Docker/Kubernetes).
- **Proxy desacoplado:** Utiliza MicroProfile Rest Client para consumir cualquier API REST externa.
- **Configuración flexible:** URLs y parámetros críticos configurables por variables de entorno.
- **Documentación OpenAPI/Swagger:** Endpoints autodescriptivos y explorables vía Swagger UI.
- **Pruebas automatizadas:** Incluye pruebas de integración para facilitar CI/CD.
- **Observabilidad:** Logging y health checks listos para monitoreo.

## Estructura del proyecto

- `src/main/java/com/external/rest/resource/ConsultaResource.java`: Endpoint proxy `GET /consulta/{claveAcceso}`.
- `src/main/java/com/external/rest/client/ExternalApiClient.java`: Cliente REST reactivo para el servicio externo.
- `src/main/resources/application.properties`: Configuración base de la aplicación.
- `src/test/java/com/external/rest/resource/ConsultaResourceTest.java`: Pruebas automatizadas para el endpoint.

## Endpoints principales

- `GET /consulta/{claveAcceso}`: Proxy para la consulta a un servicio REST externo.
- `GET /q/health`: Health check estándar de Quarkus.
- `GET /q/swagger-ui`: Documentación interactiva OpenAPI.

## Configuración y despliegue

La URL del servicio externo se configura a través de la propiedad `external-api/mp-rest/url` en `application.properties` o mediante una variable de entorno.

### Variable de entorno recomendada

- `QUARKUS_REST_CLIENT_EXTERNAL_API_URL`: URL base del servicio externo (sobrescribe `application.properties`).

#### Ejemplo en desarrollo (Windows Git Bash, Linux, Mac)
```sh
export QUARKUS_REST_CLIENT_EXTERNAL_API_URL="https://api.externa.com"
./mvnw compile quarkus:dev
```

#### Ejemplo en Docker
```sh
docker run -e QUARKUS_REST_CLIENT_EXTERNAL_API_URL="https://api.externa.com" -i --rm -p 8080:8080 your-image-name
```

### Documentación de la API

- **Swagger UI:** [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)
- **OpenAPI Spec:** [http://localhost:8080/q/openapi](http://localhost:8080/q/openapi)

## Ejemplo de uso del endpoint

```sh
curl http://localhost:8080/consulta/123456789012345678901234567890123456789012345
```

## Pruebas

El proyecto incluye pruebas de integración con RestAssured. Para ejecutarlas:
```sh
./mvnw test
```

## Requisitos

- Java 21+
- Maven 3.9.11+
- Docker (opcional, para despliegue en contenedores)

## Referencias

- [Quarkus](https://quarkus.io/)
- [Mutiny](https://smallrye.io/smallrye-mutiny/)
- [MicroProfile Rest Client](https://github.com/eclipse/microprofile-rest-client)
