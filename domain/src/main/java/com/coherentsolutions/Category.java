package com.coherentsolutions;

import java.util.List;

public abstract class Category {
    private String name;
    private CategoryType type;
    private List<Product> productList;

    public Category(String name, CategoryType type) {
        this.name = name;
    }


}
