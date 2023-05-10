package com.coherentsolutions.store;

import com.coherentsolutions.domain.Product;
import com.github.javafaker.Faker;

public class RandomProductGenerator {
    private Faker faker = new Faker();

    public String setName(String category) {
        switch (category) {
            case "Book":
                return faker.book().title();
            case "Food":
                return faker.food().dish();
            case "Phone":
                return faker.company().name();
            default:
                return "Random Product";
        }
    }

    public int setPrice(Product product) {
        return faker.number().numberBetween(1, 1000);
    }

    public int setRate(Product product) {
        return faker.number().numberBetween(1, 10);
    }

}