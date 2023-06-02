package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class Store {
    private List<Category> categoryList = new ArrayList<>();

    public Store() {
    }

    public void addCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category must not be null or empty");
        }
        if (!checkForDuplicates(category)) {
            categoryList.add(category);
        } else {
            System.out.println("This Category already added to the store");
        }
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void printCategoryWithProducts() {
        if (categoryList.isEmpty()) {
            System.out.println("Store is empty");
        } else {
            categoryList.forEach(category -> category.printCategoryWithProducts());
        }
    }

    public List<Product> getAllProducts() {
       return categoryList.stream()
                .map(Category::getProductList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        categoryList.clear();
    }


    public boolean checkForDuplicates(Category newCategory) {
        return categoryList.stream().allMatch(category -> category.equals(newCategory));
    }
}

