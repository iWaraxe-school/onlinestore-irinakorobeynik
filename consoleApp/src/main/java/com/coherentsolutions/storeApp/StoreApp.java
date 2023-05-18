package com.coherentsolutions.storeApp;
import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;
import java.lang.reflect.InvocationTargetException;

public class StoreApp {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Store onlinestore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlinestore);
        randomStorePopulator.FillStoreWithProduct();
        //onlinestore.printCategoryWithProducts();

    }
}







