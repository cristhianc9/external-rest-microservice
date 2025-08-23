package com.external.rest.resource;

import com.external.rest.client.ExternalApiClient;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import io.smallrye.mutiny.Uni;

@QuarkusTest
public class ConsultaResourceTest {

    @InjectMock
    @RestClient
    ExternalApiClient externalApiClient;

    @Test
    public void testConsultaEndpointMock() {
        String clave = "1234567890";
        String jsonResponse = """
                {
                    "seccion": "autorizacion",
                    "estadoOnline": "OK",
                    "claveAcceso": "%s"
                }
                """.formatted(clave);

        when(externalApiClient.consultarPorClave(clave))
                .thenReturn(Uni.createFrom().item(Response.ok(jsonResponse).header("Content-Type", "application/json").build()));

        RestAssured.given()
            .when().get("/consulta/" + clave)
            .then()
            .statusCode(200)
            .body("seccion", equalTo("autorizacion"))
            .body("estadoOnline", equalTo("OK"))
            .body("claveAcceso", equalTo(clave));
    }
}
