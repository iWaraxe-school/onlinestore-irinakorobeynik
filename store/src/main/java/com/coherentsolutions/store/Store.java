package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categoryList = new ArrayList<>();

    public Store() {
    }

    public void addCategory(Category category) {
        categoryList.add(category);
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

    public void deleteAll() {
        categoryList.clear();
    }
}

