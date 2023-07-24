package com.coherentsolutions.store;

import com.coherentsolutions.db.CategoriesDAO;
import com.coherentsolutions.db.ProductsDAO;
import com.coherentsolutions.domain.*;

import java.util.List;

import java.util.stream.IntStream;


public class RandomStorePopulator {

    private final Store store;

/* these final variables are used to declare boundary values of product amount for the category*/
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

    /* creates instances of classes that extends
    Category class and add them to CategoryList */
    private void fillStoreWithCategories() {
/*        Reflections reflections = new Reflections("com.coherentsolutions.domain");
        Set<Class<? extends Category>> allClasses = reflections.getSubTypesOf(Category.class);
        CategoryFactory categoryFactory = new CategoryFactory();
        allClasses.forEach(allClass -> {
            try {
                this.store.addCategory(allClass.getConstructor().newInstance());
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException("Something goes wrong.Please Contact to Administrator");
            }
        });*/
        categoriesDBHelper.createCategoriesTable();
        if (categoriesDBHelper.getAllCategories().isEmpty()){
            List<String> categoryTypes = CategoryType.getCategoryTypesList();
            categoryTypes.forEach(category -> {
                categoriesDBHelper.addCategoryEntry(category);
                this.store.addCategory(CategoryFactory.createCategory(CategoryType.valueOf(category)));
            });
        }
    }
}

