package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class RandomStorePopulator {

    private Store store;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public void FillStoreWithProduct() {
        RandomProductGenerator randomProductGenerator = new RandomProductGenerator();
        int counter = nextInt(0, 10);
        List<Category> categoryList = new ArrayList<>();
        for (Category category : categoryList) {
            for (int i = 0; i < counter; i++) {
                Product product = new Product(randomProductGenerator.generateName(category.getName()),
                        randomProductGenerator.generateRate(),
                        randomProductGenerator.generatePrice());
                category.addProduct(product);
            }
        }

    }

    public void FillStoreWithCategories() {

    }
}
