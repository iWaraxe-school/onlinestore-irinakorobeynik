package com.coherentsolutions.sorting;

public enum SortingTypes {
    asc, desc;


    public static SortingTypes findByName(String value) {
        for (SortingTypes type : values()) {
            {
                if (type.name().equalsIgnoreCase(value)) {
                    return type;
                }
            }
        }
        try {
            return null;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Inappropriate value");
        }

    }
}