package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class RandomStorePopulator {

    private final Store store;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 10;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    /*gets CategoryList filled in method below and fill each of them
    with random quantity of products generated with Faker*/
    public void fillStoreWithProduct() {
        RandomProductGenerator randomProductGenerator = new RandomProductGenerator();
        fillStoreWithCategories();
        List<Category> categoryList = this.store.getCategoryList();
        for (Category category : categoryList) {
            for (int i = 0; i < nextInt(MIN_VALUE, MAX_VALUE); i++) {
                Product product = new Product(randomProductGenerator.generateName(category.getName()),
                        randomProductGenerator.generateRate(),
                        randomProductGenerator.generatePrice());
                category.addProduct(product);
            }
        }
    }

    /* creates instances of classes that extends
    Category class and add them to CategoryList */
    private void fillStoreWithCategories()  {
        Reflections reflections = new Reflections("com.coherentsolutions.domain");
        Set<Class<? extends Category>> allClasses = reflections.getSubTypesOf(Category.class);
        List<Category> categoryList = new ArrayList<>();
        for (Class<? extends Category> allClass : allClasses) {
            try {
                this.store.addCategory(allClass.getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException("Something goes wrong.Please Contact to Administrator");
            }
        }
    }
}

