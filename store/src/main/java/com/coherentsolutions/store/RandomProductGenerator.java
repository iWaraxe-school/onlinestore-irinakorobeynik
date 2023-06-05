package com.coherentsolutions.store;

import com.github.javafaker.Faker;

public class RandomProductGenerator {
    private static final int MIN_PRICE = 1;
    private static final int MAX_PRICE = 1000;
    private static final int MIN_RATE = 1;
    private static final int MAX_RATE = 10;
    private final Faker faker = new Faker();

    /* generates string value based on category selected (Book,Food,Phone)*/
    public String generateName(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category must not be null or empty");
        }
        switch (category) {
            case "Book":
                return faker.book().title();
            case "Food":
                return faker.food().dish();
            case "Phone":
                return faker.company().name();
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }
    }

    public int generatePrice() {
        return faker.number().numberBetween(MIN_PRICE, MAX_PRICE);
    }

    public int generateRate() {
        return faker.number().numberBetween(MIN_RATE, MAX_RATE);
    }

}
