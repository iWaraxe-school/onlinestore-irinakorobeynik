package com.coherentsolutions.order;

import com.coherentsolutions.store.Store;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.reflections.Reflections.log;

public class CreateOrder implements Runnable {
    private Order order = Order.getInstance();
    @Override
    public void run() {
        Random random = new Random();
        int randomNumber = random.nextInt(30);
        System.out.println("Start Thread " + Thread.currentThread().getName());
        order.addProductToCart(Store.getInstance().generateRandomProduct());
        order.printOrder();
        try {
            TimeUnit.SECONDS.sleep(randomNumber);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread was interrupted", e);
        }
        System.out.println("Finish thread " + Thread.currentThread().getName());

    }
}
