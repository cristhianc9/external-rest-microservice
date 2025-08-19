package com.external.rest.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ConsultaResourceTest {

    @Test
    public void testConsultaEndpointMock() {
        String clave = "1234567890";
        RestAssured.given()
            .when().get("/consulta/" + clave)
            .then()
            .statusCode(200)
            .body("estado", equalTo("OK"))
            .body("mensaje", equalTo("Mock OK"));
    }
}
