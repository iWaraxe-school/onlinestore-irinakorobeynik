package com.coherentsolutions.db;
import java.sql.*;

public class DBHelper {

    public final static String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=online-store;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public static Connection setConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
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
        try (Connection connection = setConnection();) {
            boolean productsExist = connection.getMetaData().getTables(null,null, "PRODUCTS", null).next();
            boolean categoriesExist = connection.getMetaData().getTables(null,null, "CATEGORIES", null).next();
            if (productsExist || categoriesExist){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE PRODUCTS");
           statement.executeUpdate("DROP TABLE CATEGORIES");}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
