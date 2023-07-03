package com.coherentsolutions.order;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.store.Store;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Order {
    private static volatile Order instance;
    private final Queue<Product> productCart = new ConcurrentLinkedQueue<>();

    private Order() {
    }

    public static Order getInstance() {
        Order result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Order.class) {
            if (instance == null) {
                instance = new Order();
            }
            return instance;
        }
    }
    public synchronized void addProductToCart (Product product){
        this.productCart.add(product);
        System.out.println(product.toString() + " ordered");
    }

    public synchronized void emptyOrder (){
        this.productCart.clear();
    }

    public void printOrder() {
        System.out.println("Order consists of:");
        this.productCart.forEach(System.out::println);

        }
    }



