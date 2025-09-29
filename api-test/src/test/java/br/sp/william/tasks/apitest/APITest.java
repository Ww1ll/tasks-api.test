package br.sp.william.tasks.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITest {

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://localhost:8082/tasks-backend";
    }

    @Test
    public void deveRetornarListagemDeTodasAsTarefas(){
        RestAssured.given()
                .log().all()
                .when()
                    .get("/todo")
                .then()
                    .statusCode(200)
                ;
    }

    @Test
    public void deveAdicionarTarefaComSucesso(){
        RestAssured.given()
                .body("{\"task\": \"Teste de insercao com restAssured\", \"dueDate\": \"2025-12-09\"}")
                .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .statusCode(201)
        ;
    }

    @Test
    public void naoDeveInserirTarefaInvalida(){
        RestAssured.given()
                    .body("{\"task\": \"Teste de insercao com restAssured\", \"dueDate\": \"2015-12-09\"}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .log().all()
                    .statusCode(400)
                    .body("message", CoreMatchers.is("Due date must not be in past"))
        ;
    }
}
