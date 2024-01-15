package com.aktie.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.aktie.integration.config.AktieTestLifeCycleManager;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
@QuarkusTestResource(AktieTestLifeCycleManager.class)
public class UserResourceTest {

    @Test
    @Order(1)
    public void testCreateUser() {
        var payload = Map.of("name", "Sandrolaxx Test", "document", "10254564392");

        given()
                .when()
                .header("Content-Type", "application/json")
                .header("dbImpl", "POSTGRES")
                .body(payload)
                .post("/api/v1/user")
                .then()
                .statusCode(200)
                .assertThat()
                .body(containsString("id"), containsString("document"));
    }

    @Test
    @Order(2)
    public void findByWithError() {
        given()
                .when()
                .header("userId", UUID.randomUUID().toString())
                .header("dbImpl", "POSTGRES")
                .get("/api/v1/user")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(3)
    public void listAllByDoc() {
        given()
                .when()
                .header("document", "10254564392")
                .header("dbImpl", "POSTGRES")
                .get("/api/v1/user/all")
                .then()
                .statusCode(200)
                .body(containsString("id"), containsString("document"));
    }

}