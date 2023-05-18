package com.coherentsolutions.storeApp;

import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;

public class StoreApp {

    public static void main(String[] args) {

        Store onlinestore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlinestore);
        randomStorePopulator.fillStoreWithProduct();
        //onlinestore.printCategoryWithProducts();

    }
}







