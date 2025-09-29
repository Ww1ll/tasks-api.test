package br.sp.william.tasks.apitest;

import io.restassured.RestAssured;
import org.junit.Test;

public class APITest {

    @Test
    public void test(){
        RestAssured.given()
                .log().all()
                .when()
                    .get("http://localhost:8082/tasks-backend/todo")
                .then()
                .log().all()
                ;
    }
}
