package com.coherentsolutions.db;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.domain.ProductBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductsDAO extends DBHelper {

    public void createProductsTable() {
        try (Connection connection = DBHelper.setConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE PRODUCTS (ID int IDENTITY(1,1) PRIMARY KEY, CATEGORY_ID INT, NAME varchar(255), PRICE INT, RATE INT, ORDERED BIT DEFAULT 0)");
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with product table creation");
        }
    }

    public void addProductEntry(Product product, int categoryId) {
        try (Connection connection = DBHelper.setConnection()) {
            String sqlScript = "INSERT INTO PRODUCTS (CATEGORY_ID, NAME, PRICE, RATE) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            preparedStatement.setInt(1,categoryId);
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getRate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with product creation");
        }
    }

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBHelper.setConnection()) {
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
            throw new RuntimeException("Something wrong with getting product list");
        }
        return products;
    }

    public static Product getRandomProduct() {
        List<Product> products = getAllProducts();
        int randomProductNumber = new Random().nextInt(products.size()) ;
        Product product = null;
        try (Connection connection = DBHelper.setConnection()) {
            if (randomProductNumber > 0) {
                String sqlScript = "SELECT * FROM PRODUCTS where id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
                preparedStatement.setInt(1, randomProductNumber);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    product = new ProductBuilder()
                            .setName(resultSet.getString("NAME"))
                            .setPrice(resultSet.getInt("PRICE"))
                            .setRate(resultSet.getInt("RATE"))
                            .build();
                }
                return product;
            }
           else {
               throw  new NullPointerException("no products in the store");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with getting random product");
        }

    }

    public void printProduct(Product product) {
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT P.NAME as PRODUCT_NAME, P.PRICE, P.RATE, C.NAME as CATEGORY_NAME" +
                    "FROM PRODUCTS P " +
                    "INNER JOIN CATEGORIES C " +
                    "ON C.ID=P.CATEGORY_ID");
            while (resultSet.next()) {

                System.out.printf("- Product %s has %2s rate and %3s price%n", resultSet.getString("PRODUCT_NAME"),
                        resultSet.getInt("RATE"), resultSet.getInt("PRICE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with product printing");
        }
    }

    public static void addToCart(Product product) {
        try (Connection connection = DBHelper.setConnection();) {
            String sqlScript = "Update PRODUCTS set ORDERED = 1 WHERE NAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            preparedStatement.setString(1,product.getName().replace("\"", ""));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with adding into cart");
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
            throw new RuntimeException("Something wrong with getting ordered product");
        }
        return product;
    }

}
