package com.coherentsolutions.http;

import com.coherentsolutions.store.Store;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static com.coherentsolutions.http.Server.handleResponse;


public class MainPage implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        handleResponse(httpExchange,"View All Products in our Store! \n Take a look on list of products we have >> \n" + Store.getInstance().getAllProducts());


    }
}
