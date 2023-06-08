package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import com.coherentsolutions.domain.ProductBuilder;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class RandomStorePopulator {

    private final Store store;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 6;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    /*gets CategoryList filled in method below and fill each of them
    with random quantity of products generated with Faker*/
    public void fillStoreWithProduct() {
        RandomProductGenerator randomProductGenerator = new RandomProductGenerator();
        fillStoreWithCategories();
        List<Category> categoryList = this.store.getCategoryList();
        categoryList.forEach(category -> IntStream.range(MIN_VALUE, MAX_VALUE).forEach(i -> {
            Product product = new ProductBuilder()
                    .setName(randomProductGenerator.generateName(category.getName()))
                            .setRate(randomProductGenerator.generateRate())
                            .setPrice(randomProductGenerator.generatePrice())
                            .build();
            category.addProduct(product);
        }));
    }

    /* creates instances of classes that extends
    Category class and add them to CategoryList */
    private void fillStoreWithCategories() {
        Reflections reflections = new Reflections("com.coherentsolutions.domain");
        Set<Class<? extends Category>> allClasses = reflections.getSubTypesOf(Category.class);
        List<Category> categoryList = new ArrayList<>();
        allClasses.forEach(allClass -> {
            try {
                this.store.addCategory(allClass.getConstructor().newInstance());
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException("Something goes wrong.Please Contact to Administrator");
            }
        });
    }
}

