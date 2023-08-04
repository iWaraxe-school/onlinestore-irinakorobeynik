package com.coherentsolutions.http;

import com.coherentsolutions.db.ProductsDAO;
import com.coherentsolutions.domain.Product;
import com.coherentsolutions.domain.ProductBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static com.coherentsolutions.http.Server.handleResponse;

public class OrderPage implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (httpExchange.getRequestMethod().equals("POST")) {
            String orderedProductInJson = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
            Product orderedProduct;
            try {
                orderedProductInJson = reader.readLine();
                JsonObject json = (JsonObject) new JsonParser().parse(orderedProductInJson);
                orderedProduct = new ProductBuilder()
                        .setName(json.get("name").toString())
                        .setPrice(json.get("price").getAsInt())
                        .setRate(json.get("rate").getAsInt())
                        .build();

                ProductsDAO.addToCart(orderedProduct);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            handleResponse(httpExchange, "The information about ordered product: " + orderedProduct.toString());
        }
        if (httpExchange.getRequestMethod().equals("GET")){
            handleResponse(httpExchange, "The information about ordered product: " + ProductsDAO.getorderedProduct());
        }
    }
}

