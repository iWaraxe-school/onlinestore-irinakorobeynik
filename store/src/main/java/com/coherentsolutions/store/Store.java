package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            for (Category category : categoryList) {
                category.printCategoryWithProducts();
                System.out.println();
            }
        }
    }

    public List<Product> getAllProducts() {
        List<Product> listOfAllProducts = new ArrayList<>();
        for (Category category : categoryList) {
            listOfAllProducts.addAll(category.getProductList());
        }
        return listOfAllProducts;
    }

    public void deleteAll() {
        categoryList.clear();
    }


    public boolean checkForDuplicates(Category category) {
        return categoryList.contains(category);
    }
}

