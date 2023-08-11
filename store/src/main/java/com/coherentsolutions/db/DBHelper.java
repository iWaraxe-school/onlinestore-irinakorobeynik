package com.coherentsolutions.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBHelper {
    private static Properties prop;

 public DBHelper(){
     try (InputStream input = Files.newInputStream(Paths.get("store/src/main/resources/config.properties"))) {
         prop = new Properties();
         prop.load(input);
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     } catch (IOException | ClassNotFoundException e) {
         throw new RuntimeException("Something wrong with property loading");
     }
 }

    public static Connection setConnection() {
        try {
            return DriverManager.getConnection(DBHelper.prop.getProperty("sqlUrl"));
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with setting connection");
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void clearDB() {
        try (Connection connection = setConnection()) {
            boolean productsExist = connection.getMetaData().getTables(null,null, "PRODUCTS", null).next();
            boolean categoriesExist = connection.getMetaData().getTables(null,null, "CATEGORIES", null).next();
            if (productsExist || categoriesExist){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE PRODUCTS");
           statement.executeUpdate("DROP TABLE CATEGORIES");}
        } catch (SQLException e) {
            throw new RuntimeException("DB cannot be prepared for command execution.");
        }
    }

}
