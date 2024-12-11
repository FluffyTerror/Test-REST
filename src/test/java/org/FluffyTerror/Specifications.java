package org.FluffyTerror;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Specifications {
    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .build();
    }

    public static void installSpecification(RequestSpecification requestSpecification){
        RestAssured.requestSpecification = requestSpecification;

    }
}
