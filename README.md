# external-rest-microservice

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .


## Configuración de la URL del endpoint externo

La URL del servicio externo (`external-api/mp-rest/url`) puede configurarse mediante variable de entorno para mayor flexibilidad.

### En desarrollo (Windows Git Bash, Linux, Mac)

```sh
export EXTERNAL_API_MP_REST_URL="https://midominio/api"
./mvnw compile quarkus:dev
```

### En ejecución directa del JAR

```sh
export EXTERNAL_API_MP_REST_URL="https://midominio/api"
java -jar target/quarkus-app/quarkus-run.jar
```

### En Docker

```sh
docker run -e EXTERNAL_API_MP_REST_URL="https://midominio/api" -i --rm -p 8080:8080 quarkus/efac-rest-microservice-jvm
```

---

## Running the application in dev mode

Puedes ejecutar la aplicación en modo desarrollo con live coding usando:
```sh
./mvnw compile quarkus:dev
```

> **_NOTA:_** Quarkus incluye una Dev UI en http://localhost:8080/q/dev/ (solo en modo dev).

## Swagger UI

La documentación interactiva de la API está disponible en:

- Swagger UI: http://localhost:8080/swagger-ui

# efac-rest-microservice

Microservicio reactivo y escalable construido con Quarkus 3, pensado como un proxy adaptable para el consumo de cualquier servicio REST externo. Permite exponer endpoints propios que delegan la consulta a APIs externas, desacoplando la lógica de integración y facilitando la reutilización, escalabilidad y observabilidad.

## ¿Para qué sirve este microservicio?

Este proyecto actúa como un **proxy reactivo** para consumir servicios REST externos. Puedes adaptarlo fácilmente para integrarte con cualquier API de terceros, centralizando la lógica de consumo, transformación y exposición de datos a través de endpoints propios.

### Casos de uso típicos
- Integración con sistemas externos (APIs públicas, privadas, legacy, etc.)
- Centralización de lógica de consumo y transformación de datos
- Exposición de endpoints internos desacoplados de proveedores externos
- Facilidad para mockear y testear integraciones

## Características principales

- **Arquitectura reactiva:** Basado en Mutiny y RESTEasy Reactive, soporta alta concurrencia y baja latencia.
- **Escalabilidad:** Listo para contenedores y orquestadores (Docker/Kubernetes).
- **Proxy desacoplado:** Utiliza MicroProfile Rest Client para consumir cualquier API REST externa.
- **Configuración flexible:** URLs y parámetros críticos configurables por variables de entorno.
- **Documentación OpenAPI/Swagger:** Endpoints autodescriptivos y explorables vía Swagger UI.
- **Pruebas automatizadas:** Incluye mocks y pruebas de integración para facilitar CI/CD.
- **Observabilidad:** Logging centralizado y health checks listos para monitoreo.

## Estructura del proyecto

 `src/main/java/com/external/rest/api/ComprobanteResource.java`: Ejemplo de endpoint proxy `/consulta/{clave}`.
 `src/main/java/com/external/rest/client/ExternalApiClient.java`: Cliente REST reactivo para el servicio externo.
 `src/main/java/com/external/rest/client/ExternalApiResponse.java`: POJO de respuesta del servicio externo.
 `src/main/resources/application.properties`: Configuración base.
 `src/test/java/com/external/rest/client/ExternalApiClientMock.java`: Mock para pruebas.
 `src/test/java/com/external/rest/resource/ConsultaResourceTest.java`: Pruebas automatizadas.

## Endpoints principales (ejemplo)

- `GET /consulta/{clave}`: Proxy para consulta a un servicio REST externo. Respuesta reactiva (`Uni<ExternalApiResponse>`).
- `GET /q/health`: Health check estándar Quarkus.
- `GET /swagger-ui`: Documentación interactiva OpenAPI.

## Configuración y despliegue

### Variables de entorno recomendadas

- `EXTERNAL_API_MP_REST_URL`: URL base del servicio externo (sobrescribe `application.properties`).

#### Ejemplo en desarrollo (Windows Git Bash, Linux, Mac)
```sh
export EXTERNAL_API_MP_REST_URL="https://midominio/api"
./mvnw compile quarkus:dev
```

#### Ejemplo en Docker
```sh
docker run -e EXTERNAL_API_MP_REST_URL="https://midominio/api" -i --rm -p 8080:8080 quarkus/efac-rest-microservice-jvm
```

#### Ejemplo en ejecución directa del JAR
```sh
export EXTERNAL_API_MP_REST_URL="https://midominio/api"
java -jar target/quarkus-app/quarkus-run.jar
```

### Swagger UI y OpenAPI

- Swagger UI: [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)
- OpenAPI: [http://localhost:8080/openapi](http://localhost:8080/openapi)

## Ejemplo de uso del endpoint

```sh
curl http://localhost:8080/consulta/1234567890
```
Respuesta esperada (mock):
```json
{
	"estado": "OK",
	"mensaje": "Mock OK"
}
```

## Pruebas y calidad

- Pruebas automáticas con Quarkus Test y RestAssured.
- Mock de cliente externo para pruebas sin dependencia real del API externo.
- Ejecuta las pruebas con:
```sh
./mvnw test
```

## Escalabilidad y buenas prácticas

- Arquitectura desacoplada: el microservicio puede escalar horizontalmente y adaptarse a diferentes entornos.
- Configuración externa: todas las URLs y parámetros críticos pueden ser gestionados por variables de entorno o sistemas de configuración centralizada.
- Observabilidad: logs claros de cada consulta externa y health checks listos para integración con Prometheus/Grafana.
- Código reactivo: preparado para cargas concurrentes y baja latencia.

## Requisitos

- Java 21+
- Maven 3.9.11+
- Docker (opcional, para despliegue en contenedores)

## Referencias

- [Quarkus](https://quarkus.io/)
- [Mutiny](https://smallrye.io/smallrye-mutiny/)
- [MicroProfile Rest Client](https://github.com/eclipse/microprofile-rest-client)
- [OpenAPI/Swagger](https://quarkus.io/guides/openapi-swaggerui)
