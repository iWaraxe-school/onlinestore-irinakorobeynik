package com.coherentsolutions.sorting;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.domain.ProductBuilder;

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
            String sortOrder = sortEntry.getValue().name();
            String sortField = sortEntry.getKey();
            int result = sortASCBySortField(sortField, o1, o2);
            if (result != 0) {
                if (sortOrder.equals(SortingTypes.asc.name())) {
                    return result;
                } else if (sortOrder.equals(SortingTypes.desc.name())) {
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
                return (int) (o1.getPrice()-(o2.getPrice()));
            }
            case "rate": {
                return (int) (o1.getRate()-o2.getRate());
            }
            default:
                return 0;
        }
    }


}

