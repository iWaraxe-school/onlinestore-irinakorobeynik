package com.coherentsolutions.order;

import java.util.concurrent.TimeUnit;

public class ClearOrder implements Runnable {
    private Order order = Order.getInstance();
    private Thread currentThread;

    @Override
    public void run() {
        currentThread = Thread.currentThread();
        while (!currentThread.isInterrupted()) {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            order.emptyOrder();
            System.out.println("Cart is empty");
        }
    }

    public void finishRunning() {
        if (currentThread != null)
            currentThread.interrupt();
    }
}
