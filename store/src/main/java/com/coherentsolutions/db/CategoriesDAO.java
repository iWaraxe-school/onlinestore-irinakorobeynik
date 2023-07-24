package com.coherentsolutions.db;

import com.coherentsolutions.domain.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            throw new RuntimeException(e);
        }
    }

    public void addCategoryEntry(String category) {
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO CATEGORIES (NAME) VALUES ('%s')", category));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCategoryEntry(Category category) {
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO CATEGORIES (NAME) VALUES ('%s')", category.getName()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCategoryId(Category category) {

        int categoryId = 0;
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT ID FROM CATEGORIES WHERE NAME = '%s'", category.getName()));
            resultSet.next();
            categoryId = resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }


    public List<Category> getAllCategories() {
        List<Category>сategoryList = new ArrayList<>();
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM CATEGORIES");
            while (rs.next()) {
                сategoryList.add(CategoryFactory.createCategory(CategoryType.valueOf(rs.getString("NAME"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return сategoryList;

    }

    public List<Product> getProductListByCategory(Category category) {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = DBHelper.setConnection();) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM PRODUCTS WHERE CATEGORY_ID = %d", getCategoryId(category)));
            while (rs.next()) {
                Product product = new ProductBuilder()
                        .setName(rs.getString("NAME"))
                        .setRate(rs.getInt("RATE"))
                        .setPrice(rs.getInt("PRICE"))
                        .build();
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }


    public void printCategoryWithProductsDB(Category category) {

        try (Connection connection = DBHelper.setConnection();) {
            List<Product> productList = getProductListByCategory(category);
            StringBuilder builder = new StringBuilder();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM CATEGORIES WHERE NAME = '%s'", category.getName()));
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
            e.printStackTrace();
        }
    }



}
