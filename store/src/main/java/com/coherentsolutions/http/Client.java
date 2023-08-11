package com.coherentsolutions.http;

import com.coherentsolutions.db.ProductsDAO;
import com.coherentsolutions.domain.Product;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Client {
    public static void OrderProduct() {
        try (InputStream input = Files.newInputStream(Paths.get("store/src/main/resources/config.properties"))) {
            Properties prop = new Properties();
            prop.load(input);
            Product orderedProduct = ProductsDAO.getRandomProduct();
            Gson g = new Gson();
            String productJson = g.toJson(orderedProduct);

            System.out.println(productJson);
            RestAssured.baseURI = "http://localhost:8080/";
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            request.auth().basic(prop.getProperty("user"), prop.getProperty("password"));
            request.body(productJson);

            Response response = request.post("/order");
            System.out.println("The status received: " + response.statusLine());
            System.out.println("Response body: " + response.getBody().asString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
