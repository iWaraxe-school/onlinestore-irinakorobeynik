package com.coherentsolutions.storeApp;

import com.coherentsolutions.db.DBHelper;
import com.coherentsolutions.order.ClearOrder;
import com.coherentsolutions.order.CreateOrder;
import com.coherentsolutions.sorting.SortHelper;
import com.coherentsolutions.store.Store;

import java.util.Scanner;

public class Menu {
    public static final String COMMAND = "Enter command:";
    public static final String FIRST_OPTION = "sort - get all products sorted based on XML config";
    public static final String SECOND_OPTION = "top - get first 5 most expensive products";
    public static final String THIRD_OPTION = "print - print whole store";
    public static final String FOURTH_OPTION = "order - make an order";
    public static final String EXIT_OPTION = "quit - End program";
    private Scanner scanner;
    private SortHelper sort;
    private Store store;
    private MenuOption menuOption;

    public Menu(Store store) {
        this.scanner = new Scanner(System.in);
        this.sort = new SortHelper(store);
        this.store = store;
        ClearOrder clearingThread = new ClearOrder();
        new Thread(clearingThread).start();
        clearingThread.finishRunning();
    }

    public String getMenuOptionName() {
        return menuOption.name();
    }

    public void showMenu() {
        while (true) {
            printMenu();
            String consoleInput = scanner.nextLine();
            switch (menuOption.findByName(consoleInput)) {
                case QUIT:
                    return;
                case SORT:
                    sort.sortProductsByXML();
                    break;
                case TOP:
                    sort.getTopFiveProducts();
                    break;
                case PRINT:
                    this.store.printCategoriesWithProducts();
                    break;
                case ORDER:
                    Runnable createOrder = new CreateOrder();
                    new Thread(createOrder).start();
                    Runnable clearOrder = new ClearOrder();
                    new Thread(clearOrder).start();
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println();
        System.out.println(COMMAND + "\n" + FIRST_OPTION + "\n" + SECOND_OPTION + "\n" + THIRD_OPTION + "\n"+ FOURTH_OPTION + "\n" + EXIT_OPTION);

    }
}
