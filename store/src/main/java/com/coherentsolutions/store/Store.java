package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.CategoryType;
import com.coherentsolutions.domain.Product;
import com.coherentsolutions.domain.ProductBuilder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Store {
    private static volatile Store instance;
    private final List<Category> categoryList = new ArrayList<>();

    public final int MAX_RANDOM_VALUE = 10;
    public final int MIN_RANDOM_VALUE = 1;


    private Store() {
    }

    public static Store getInstance() {
        Store result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Store.class) {
            if (instance == null) {
                instance = new Store();
            }
            return instance;
        }
    }

    public void addCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category must not be null or empty");
        } else if (!isCategoryPresent(category)) {
            categoryList.add(category);
        } else {
            System.out.println("This Category already added to the store");
        }
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void printCategoriesWithProducts() {
        if (categoryList.isEmpty()) {
            System.out.println("Store is empty");
        } else {
            categoryList.forEach(Category::printCategoryWithProducts);
        }
    }

    public List<Product> getAllProducts() {
        try {
            return categoryList.stream()
                    .map(Category::getProductList)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("No products in the store");
        }
    }

    public void deleteAll() {
        categoryList.clear();
    }

    public boolean isCategoryPresent(Category newCategory) {
        return categoryList.stream().anyMatch(category -> category.equals(newCategory));
    }

    public Product selectRandomProduct() {
        RandomProductGenerator products = new RandomProductGenerator();
        Product product = new ProductBuilder()
                .setName(products.generateName(CategoryType.getRandomTypeName()))
                .setRate(products.generateRate())
                .setPrice(products.generatePrice())
                .build();
        return product;
    }

}

