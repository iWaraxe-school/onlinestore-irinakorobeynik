package com.coherentsolutions.order;

import com.coherentsolutions.store.Store;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class CreateOrder implements Runnable {
    private static Logger log = Logger.getLogger("com.coherentsolutions");
    private Order order = Order.getInstance();

    @Override
    public void run() {
        int randomNumber = new Random().nextInt(30);
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
