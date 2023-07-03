package com.coherentsolutions.order;
import com.coherentsolutions.store.Store;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateOrder implements Runnable {
    private Order order = Order.getInstance();
    @Override
    public void run() {
        Random random = new Random();
        int randomNumber = random.nextInt(30);
        System.out.println("Start Thread " + Thread.currentThread().getName());
        order.addProductToCart(Store.getInstance().selectRandomProduct());
        order.printOrder();
        try {
            TimeUnit.SECONDS.sleep(randomNumber);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finish thread " + Thread.currentThread().getName());
        Runnable clearOrder = new ClearOrder();
        new Thread(clearOrder).start();




    }
}