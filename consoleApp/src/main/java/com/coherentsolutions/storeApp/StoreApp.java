package com.coherentsolutions.storeApp;

import com.coherentsolutions.sorting.SortHelper;
import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;
import java.util.Scanner;

public class StoreApp {

    public static void main(String[] args) {

        Store onlinestore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlinestore);
        randomStorePopulator.fillStoreWithProduct();
        SortHelper sort = new SortHelper(onlinestore);

        while (true) {
            System.out.println("Enter number:");
            System.out.println("1 - get all products sorted based on XML config");
            System.out.println("2 - get first 5 most expensive products");
            System.out.println("3 - print whole store");
            System.out.println("0 - End program");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            System.out.println(num);
            switch (num) {
                case 0:
                    return;
                case 1:
                    sort.sortProductsByXML();
                    break;
                case 2:
                    sort.getTopFiveProducts();
                    break;
                case 3:
                    onlinestore.printCategoryWithProducts();
                    break;
            }
        }
    }
    }








