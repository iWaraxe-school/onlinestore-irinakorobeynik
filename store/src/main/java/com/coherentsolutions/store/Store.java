package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
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
        if (category == null ) {
            throw new IllegalArgumentException("Category must not be null or empty");
        }
        if (checkForDuplicates() == true ) {
            System.out.println ("This Category already added to the store");
        }

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

    public boolean checkForDuplicates (){
        Set<Category> set = new HashSet<Category> (categoryList);
        return set.size() < categoryList.size();
    }
}

