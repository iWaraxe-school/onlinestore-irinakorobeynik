package com.coherentsolutions.db;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.domain.ProductBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductsDAO extends DBHelper {

    public void createProductsTable() {
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("CREATE TABLE PRODUCTS (ID int IDENTITY(1,1) PRIMARY KEY, CATEGORY_ID INT, NAME varchar(255), PRICE INT, RATE INT, ORDERED BIT DEFAULT 0)"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addProductEntry(Product product, int categoryId) {
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO PRODUCTS (CATEGORY_ID, NAME, PRICE, RATE) VALUES (%d, '%s', %2d, %3d)", categoryId, product.getName(), product.getPrice(), product.getRate()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS");
            while (resultSet.next()) {
                Product product = new ProductBuilder()
                        .setName(resultSet.getString("NAME"))
                        .setPrice(resultSet.getInt("PRICE"))
                        .setRate(resultSet.getInt("RATE"))
                        .build();
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static Product getRandomProduct() {
        List<Product> products = getAllProducts();
        Product product = null;
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM PRODUCTS where id = %d", new Random().nextInt(products.size())));
            while (resultSet.next()) {
                product = new ProductBuilder()
                        .setName(resultSet.getString("NAME"))
                        .setPrice(resultSet.getInt("PRICE"))
                        .setRate(resultSet.getInt("RATE"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void printProduct(Product product) {
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT P.NAME as PRODUCT_NAME, P.PRICE, P.RATE, C.NAME as CATEGORY_NAME" +
                    "FROM PRODUCTS P " +
                    "INNER JOIN CATEGORIES C " +
                    "ON C.ID=P.CATEGORY_ID");
            while (resultSet.next()) {

                String.format("- Product %s has %2s rate and %3s price", resultSet.getString("PRODUCT_NAME"),
                        resultSet.getInt("RATE"), resultSet.getInt("PRICE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToCart(Product product) {
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("Update PRODUCTS set ORDERED = 1 WHERE NAME = '%s'", product.getName().replace("\"", "")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Product getorderedProduct() {
        Product product = null;
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS where ORDERED = 1");
            while (resultSet.next()) {
                product = new ProductBuilder()
                        .setName(resultSet.getString("NAME"))
                        .setPrice(resultSet.getInt("PRICE"))
                        .setRate(resultSet.getInt("RATE"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

}
