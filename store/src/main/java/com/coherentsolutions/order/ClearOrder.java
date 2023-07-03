package com.coherentsolutions.order;

import java.util.concurrent.TimeUnit;

public class ClearOrder implements Runnable {
    private Order order = Order.getInstance();
    public boolean needRun = true;

    @Override
    public void run() {
        while (needRun) {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            order.emptyOrder();
            System.out.println("Cart is empty");
        }

    }

    public void finishRunning(){
        needRun = false;
    }
}
