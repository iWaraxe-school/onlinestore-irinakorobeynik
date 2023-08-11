package com.coherentsolutions.store;

import com.coherentsolutions.db.CategoriesDAO;
import com.coherentsolutions.db.ProductsDAO;
import com.coherentsolutions.domain.*;

import java.util.List;

import java.util.stream.IntStream;


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
        categoriesDBHelper = new CategoriesDAO();
    }

    /*gets CategoryList filled in method below and fill each of them
    with random quantity of products generated with Faker*/
    public void fillStoreWithProduct() {
        RandomProductGenerator randomProductGenerator = new RandomProductGenerator();
        productsDBHelper.createProductsTable();
        fillStoreWithCategories();
        categoryList= categoriesDBHelper.getAllCategories();
        categoryList.forEach(category -> IntStream.range(MIN_VALUE, MAX_VALUE).forEach(i -> {
            Product product = new ProductBuilder()
                    .setName(randomProductGenerator.generateName(category.getName()))
                            .setRate(randomProductGenerator.generateRate())
                            .setPrice(randomProductGenerator.generatePrice())
                            .build();
            productsDBHelper.addProductEntry(product, categoriesDBHelper.getCategoryId(category));
        }));
    }

    private void fillStoreWithCategories() {

        categoriesDBHelper.createCategoriesTable();
        if (categoriesDBHelper.getAllCategories().isEmpty()){
            List<String> categoryTypes = CategoryType.getCategoryTypesList();
            categoryTypes.forEach(category -> {
                categoriesDBHelper.addCategoryEntry(CategoryFactory.createCategory(CategoryType.valueOf(category.toUpperCase())));
                this.store.addCategory(CategoryFactory.createCategory(CategoryType.valueOf(category)));
            });
        }
    }
}

