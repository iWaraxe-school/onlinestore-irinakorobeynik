package com.coherentsolutions.sorting;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.store.Store;

import java.util.*;

public class SortHelper {
    public static final String FILE_PATH = "store/src/main/resources/config.xml";
    private Store store;
    List <Product> productList;
    List <Product> sortedList;

    ProductComparator comparator;

    public SortHelper(Store store) {
        this.store = store;
        XMLParser parser = new XMLParser();
        parser.parseSortOptions(FILE_PATH);
        Map<String, SortingTypes> configMap = parser.getParsedMap();
        comparator = new ProductComparator(configMap);
        productList = this.store.getAllProducts();

    }

    public void sortProductsByXML(){
        Collections.sort(productList,comparator);
        printListProduct(productList);



    }

    public void getTopFiveProducts(){
        Collections.sort(productList, Comparator.comparing(Product::getPrice));
        sortedList.addAll(productList.subList(0,4));
        printListProduct(sortedList);

    }

    public void printListProduct(List<Product> productList) {
        productList.forEach(System.out::println);
    }


}
