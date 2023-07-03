package com.coherentsolutions.domain;

public class ProductBuilder {
    private String name;
    private int rate;
    private int price;
    public ProductBuilder() {
    }
    public String getName() {
        return name;
    }

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public int getRate() {
        return rate;
    }

    public ProductBuilder setRate(int rate) {
        this.rate = rate;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public ProductBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public Product build() {
        return new Product(this);
    }


}
