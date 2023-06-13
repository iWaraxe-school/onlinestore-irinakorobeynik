package com.coherentsolutions.domain;

import java.util.*;

public abstract class Category {
    private String name;
    private List<Product> productList = new ArrayList<Product>();

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void printCategoryWithProducts() {
        StringBuilder builder = new StringBuilder();
        if (productList.isEmpty()) {
            builder.append("the Category " + name + " has no products");
        } else {
            builder.append("The Category " + name + " has the following products: " + "\n");
            productList.stream().map(product -> product + "\n").forEach(builder::append);
        }
        System.out.println(builder);
    }
}
