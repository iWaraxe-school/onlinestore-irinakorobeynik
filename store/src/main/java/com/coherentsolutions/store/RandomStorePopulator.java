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

    private Store store;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public void FillStoreWithProduct() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        RandomProductGenerator randomProductGenerator = new RandomProductGenerator();
        FillStoreWithCategories();
        List<Category> categoryList = this.store.getCategoryList();
        int counter = nextInt(1, 10);
        for (Category category : categoryList) {
            for (int i = 0; i < counter; i++) {
                Product product = new Product(randomProductGenerator.generateName(category.getName()),
                        randomProductGenerator.generateRate(),
                        randomProductGenerator.generatePrice());
                category.addProduct(product);
            }

        }

    }

    private void FillStoreWithCategories() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Reflections reflections = new Reflections("com.coherentsolutions.domain");
        Set<Class<? extends Category>> allClasses = reflections.getSubTypesOf(Category.class);
        List<Category> categoryList = new ArrayList<>();
        for (Class<? extends Category> allClass : allClasses) {
            this.store.addCategory(allClass.getConstructor().newInstance());
        }
    }
}

