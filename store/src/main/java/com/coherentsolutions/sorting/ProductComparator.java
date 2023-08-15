package com.coherentsolutions.sorting;

import com.coherentsolutions.domain.Product;

import java.util.Comparator;
import java.util.Map;

public class ProductComparator implements Comparator<Product> {

    Map<String, SortingTypes> sortingOption;

    public ProductComparator(Map<String, SortingTypes> sortingOption) {
        this.sortingOption = sortingOption;
    }

    @Override
    public int compare(Product o1, Product o2) {
        for (Map.Entry<String, SortingTypes> sortEntry : sortingOption.entrySet()) {
            String sortField = sortEntry.getKey();
            int result = sortASCBySortField(sortField, o1, o2);
            if (result != 0) {
                if (sortEntry.getValue() == SortingTypes.asc) {
                    return result;
                } else if (sortEntry.getValue() == SortingTypes.desc) {
                    return result * -1;
                }
            }
        }
        return 0;
    }

    private int sortASCBySortField(String sortField, Product o1, Product o2) {
        switch (sortField) {
            case "name": {
                return o1.getName().compareTo(o2.getName());
            }
            case "price": {
                return  Integer.compare(o1.getPrice(), o2.getPrice());
            }
            case "rate": {
                return  Integer.compare(o1.getRate(), o2.getRate());
            }
            default:
                throw new IllegalArgumentException("Invalid soritng value");
        }
    }


}

