package com.coherentsolutions.domain;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CategoryType {
    BOOK,PHONE,FOOD;


    public static String getRandomTypeName(){
        List<String> enumNames = Stream.of(CategoryType.values())
                .map(CategoryType::name)
                .collect(Collectors.toList());
        return enumNames.get(new Random().nextInt(enumNames.size()));
    }
}
