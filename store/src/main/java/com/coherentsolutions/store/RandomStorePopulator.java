package com.coherentsolutions.store;

import com.coherentsolutions.db.CategoriesDAO;
import com.coherentsolutions.db.ProductsDAO;
import com.coherentsolutions.domain.Category;

import java.util.List;


public class RandomStorePopulator {

    private final Store store;

    /* these final variables are used to declare boundary values of product amount for the category. The values are determined with the task*/
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 6;

    private CategoriesDAO categoriesDBHelper;
    private ProductsDAO productsDBHelper;
    private List<Category> categoryList;

    public RandomStorePopulator(Store store) {
        this.store = store;
        productsDBHelper = new ProductsDAO();
    }

    public void fillStoreWithProduct() {
        productsDBHelper.fillStoreWithProduct(MIN_VALUE, MAX_VALUE, categoryList);
    }


}

