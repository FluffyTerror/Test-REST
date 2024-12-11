package org.FluffyTerror;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.ObjectPojo;

import java.util.List;

import static io.restassured.RestAssured.given;


public class RestTest {
    @Test
    void addNewVeggie() {
        Specifications.installSpecification(Specifications.requestSpecification("http://localhost:8080"));
        String data = """
        {
            "name": "Артишок",
            "type": "VEGETABLE",
            "exotic": true
        }
        """;

        Response response = given()//тут сохраняем в объект типа response чтобы в дальнейшем вытащить id сессии (куки)
                .basePath("/api/food")
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        //вот тут извлекаем куки
        String jsessionId = response.getCookie("JSESSIONID");
        System.out.println("____________________________________________________");
        System.out.println("JSESSIONID: " + jsessionId);
        System.out.println("____________________________________________________");
        List<ObjectPojo> objects = given()
                .basePath("/api/food")
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", jsessionId) // Передаем cookie
                .when()
                .get("/")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath().getList("", ObjectPojo.class);

        assert objects.size() == 5 : "Ожидалось 5 элементов, найдено " + objects.size();
        System.out.println("____________________________________________________");
        System.out.println("Список товаров:");
        objects.forEach(obj -> System.out.println(obj.getName() + " - " + obj.getType() + " - " + obj.isExotic()));
        reset();
    }

    @Test
    void addNewFruity() {
        Specifications.installSpecification(Specifications.requestSpecification("http://localhost:8080"));
        String data = """
                {
                    "name": "Груша",
                    "type": "FRUIT",
                    "exotic": false
                }
                """;

        Response response = given()//тут сохраняем в объект типа response чтобы в дальнейшем вытащить id сессии (куки)
                .basePath("/api/food")
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        //вот тут извлекаем куки
        String jsessionId = response.getCookie("JSESSIONID");
        System.out.println("____________________________________________________");
        System.out.println("JSESSIONID: " + jsessionId);
        System.out.println("____________________________________________________");
        List<ObjectPojo> objects = given()
                .basePath("/api/food")
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", jsessionId) // Передаем cookie
                .when()
                .get("/")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath().getList("", ObjectPojo.class);

        assert objects.size() == 5 : "Ожидалось 5 элементов, найдено " + objects.size();
        System.out.println("____________________________________________________");
        System.out.println("Список товаров:");
        objects.forEach(obj -> System.out.println(obj.getName() + " - " + obj.getType() + " - " + obj.isExotic()));
        reset();

    }

    public static void reset() {
        given()
                .basePath("/api/data/reset")
                .when()
                .post();
        RestAssured.reset();
    }
}

