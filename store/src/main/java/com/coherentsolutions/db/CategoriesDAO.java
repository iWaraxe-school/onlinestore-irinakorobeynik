package com.coherentsolutions.db;

import com.coherentsolutions.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO extends DBHelper {

    public void createCategoriesTable() {
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            statement.executeUpdate( "CREATE TABLE CATEGORIES (ID int NOT NULL IDENTITY(1,1), "
                    + "NAME varchar(255) NOT NULL, "
                    + "PRIMARY KEY (ID))");
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with category table creation");
        }
    }


    public void addCategoryEntry(Category category) {
        try (Connection connection = DBHelper.setConnection();) {
            String sqlScript = "INSERT INTO CATEGORIES (NAME) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with Category adding");
        }
    }

    public int getCategoryId(Category category) {

        int categoryId = 0;
        try (Connection connection = DBHelper.setConnection();) {
            String sqlScript = "SELECT ID FROM CATEGORIES WHERE NAME = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            preparedStatement.setString(1, category.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            categoryId = resultSet.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with getting Category ID");
        }
        return categoryId;
    }


    public List<Category> getAllCategories() {
        List<Category>сategoryList = new ArrayList<>();
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM CATEGORIES");
            while (rs.next()) {
                сategoryList.add(CategoryFactory.createCategory(CategoryType.valueOf(rs.getString("NAME").toUpperCase())));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with getting CategoryList");
        }
        return сategoryList;

    }

    public List<Product> getProductListByCategory(Category category) {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = DBHelper.setConnection();) {
            String sqlScript = "SELECT * FROM PRODUCTS WHERE CATEGORY_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            preparedStatement.setInt(1, getCategoryId(category));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new ProductBuilder()
                        .setName(rs.getString("NAME"))
                        .setRate(rs.getInt("RATE"))
                        .setPrice(rs.getInt("PRICE"))
                        .build();
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with getting ProductList for Category");
        }
        return productList;
    }


    public void printCategoryWithProductsDB(Category category) {

        try (Connection connection = DBHelper.setConnection();) {
            List<Product> productList = getProductListByCategory(category);
            StringBuilder builder = new StringBuilder();
            String sqlScript = "SELECT * FROM CATEGORIES WHERE NAME = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            preparedStatement.setString(1, category.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String categoryName = resultSet.getString("NAME");
            if (productList.isEmpty()) {
                builder.append("the Category " + categoryName + " has no products");
            } else {
                builder.append("The Category " + categoryName + " has the following products: " + "\n");
                productList.stream().map(product -> product + "\n").forEach(builder::append);
            }
            System.out.println(builder);
        } catch (SQLException e) {
            throw new RuntimeException("Something wrong with printing categories and products");
        }
    }


}
