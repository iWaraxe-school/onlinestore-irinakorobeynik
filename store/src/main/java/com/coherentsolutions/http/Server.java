package com.coherentsolutions.http;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {
    public static void runServer() {
        HttpServer server = null;
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
            HttpContext allProductPage = server.createContext("/", new MainPage());
            HttpContext order = server.createContext("/order", new OrderPage());
            allProductPage.setAuthenticator(new Auth("get"));
            order.setAuthenticator(new Auth("post"));
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            serverStop();
            throw new RuntimeException("Something wrong with server start : " + e);
        }
    }

    public static void handleResponse(HttpExchange exchange, String data) throws IOException {
        exchange.sendResponseHeaders(200,data.length());
        OutputStream out = exchange.getResponseBody();
        out.write(data.getBytes());
        out.close();
    }

    public static void serverStop() {
        System.exit(0);
    }


}
