package com.coherentsolutions;

import java.util.List;

public abstract class Category {
    private String name;
    private CategoryType type;
    private List<Product> productList;

    public Category(String name, CategoryType type) {
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

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", productList=" + productList +
                '}';
    }
}
