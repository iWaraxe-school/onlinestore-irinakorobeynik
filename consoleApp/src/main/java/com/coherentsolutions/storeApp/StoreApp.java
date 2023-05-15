package com.coherentsolutions.storeApp;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.FoodCategory;
import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class StoreApp {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Store onlinestore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlinestore);
        Category food = new FoodCategory();
        onlinestore.deleteAll();
        randomStorePopulator.FillStoreWithProduct();
        onlinestore.printCategoryWithProducts();

    }
}







