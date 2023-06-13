package com.coherentsolutions.storeApp;

public enum MenuOption {
    QUIT, SORT, TOP, PRINT;

    public static MenuOption findByName(String value) {
        for (MenuOption menuOption : values()) {
            {
                if (menuOption.name().equalsIgnoreCase(value.trim())) {
                    return menuOption;
                }
            }
        }
        return MenuOption.QUIT;
    }

}
