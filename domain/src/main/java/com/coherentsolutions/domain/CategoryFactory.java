package com.coherentsolutions.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategoryFactory {
    List<Category> categorylist = new ArrayList<Category>();
    public List<Category> createCategory(CategoryType type) {

        Category category = null;
        switch (type) {
            case BOOK:
                category=new BookCategory();
                break;
            case FOOD:
                category=new FoodCategory();
                break;
            case PHONE:
                category=new PhoneCategory();
                break;
        }
        categorylist.add(category);
        return categorylist;
    }
}
