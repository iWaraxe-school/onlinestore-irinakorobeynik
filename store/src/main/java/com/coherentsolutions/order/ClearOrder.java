package com.coherentsolutions.order;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
public class ClearOrder implements Runnable {
    private Order order = Order.getInstance();
    private static Logger log = Logger.getLogger(ClearOrder.class.getName());
    private volatile Thread currentThread;

    @Override
    public void run() {
        currentThread = Thread.currentThread();
        while (!currentThread.isInterrupted()) {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread was interrupted", e);
                break;
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
