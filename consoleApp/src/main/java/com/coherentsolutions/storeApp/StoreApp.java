package com.coherentsolutions.storeApp;

import com.coherentsolutions.db.DBHelper;
import com.coherentsolutions.http.Client;
import com.coherentsolutions.http.Server;
import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;

public class StoreApp {

    public static void main(String[] args) {

        DBHelper dbHelper = new DBHelper();
        dbHelper.clearDB();
        Store onlinestore = Store.getInstance();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlinestore);
        randomStorePopulator.fillStoreWithProduct();
        Server.runServer();
        Client.OrderProduct();

    }
    }








