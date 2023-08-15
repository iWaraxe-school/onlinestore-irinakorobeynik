package com.coherentsolutions.domain;

public class Product {
    private final String name;
    private final int rate;
    private final int price;

    public Product(ProductBuilder productBuilder) {
        this.name = productBuilder.getName();
        this.rate = productBuilder.getRate();
        this.price = productBuilder.getPrice();

    }
    public String getName() {
        return name;
    }
    public int getRate() {
        return rate;
    }
    public int getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return String.format("- Product %s has %2s rate and %3s price", name,rate,price);
    }


}


