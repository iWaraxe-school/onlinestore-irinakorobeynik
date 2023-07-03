package com.coherentsolutions.sorting;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.store.Store;

import java.util.*;
import java.util.stream.Collectors;

public class SortHelper {
    public static final String FILE_PATH = "store/src/main/resources/config.xml";
    private Store store;
    List<Product> productList;
    ProductComparator comparator;

    public SortHelper(Store store) {
        this.store = store;
        XMLParser parser = new XMLParser();
        parser.parseSortOptions(FILE_PATH);
        Map<String, SortingTypes> configMap = parser.getParsedMap();
        comparator = new ProductComparator(configMap);
        productList = this.store.getAllProducts();

    }

    public void sortProductsByXML() {
        List<Product> sortedList = productList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        printListProduct(sortedList);
    }

    public void getTopFiveProducts() {
        if (productList.size() >= 5) {
            List<Product> sortedList = productList.stream()
                    .sorted(Comparator.comparing(Product::getPrice).reversed())
                    .limit(5)
                    .collect(Collectors.toList());
            printListProduct(sortedList);
        } else {
            System.out.println("Product has less than 5 products");
        }

    }

    public void printListProduct(List<Product> productList) {
        productList.forEach(System.out::println);
    }


}
