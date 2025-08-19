package com.external.rest.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ComprobanteResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/comprobantes")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

}
