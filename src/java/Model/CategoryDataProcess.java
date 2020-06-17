/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Category;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ngoc Do Minh
 */
public class CategoryDataProcess {

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://ec2-35-174-127-63.compute-1.amazonaws.com:5432;d32skbnmf07buj";
            String user = "ufhdpytmfmqhpe";
            String password = "124d63e19192c08179e055cf6314adc47e5e9567133c38981ba89d6916435ffb";
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDataProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public List<Category> getData() {
        List<Category> categoryList = new ArrayList<Category>();
        String sql = "SELECT * FROM tblCategory";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryID(resultSet.getString(1));
                category.setCategoryName(resultSet.getString(2));
                category.setCategoryDescription(resultSet.getString(3));
                categoryList.add(category);
            }
            resultSet.close();
            statement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryList;
    }

    public Category getDataByID(String categoryID) {
        Category category = new Category();
        String sql = "SELECT * FROM tblCategory WHERE categoryID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, categoryID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                category.setCategoryID(resultSet.getString(1));
                category.setCategoryName(resultSet.getString(2));
                category.setCategoryDescription(resultSet.getString(3));
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }

    public boolean addCategory(String categoryID, String categoryName, String categoryDescription) {
        boolean isInsert = false;
        String sql = "INSERT INTO tblCategory VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, categoryID);
            preparedStatement.setString(2, categoryName);
            preparedStatement.setString(3, categoryDescription);
            isInsert = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isInsert;
    }

    public boolean deleteCategory(String categoryID) {
        boolean isDelete = false;
        String sql = "DELETE tblCategory WHERE categoryID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, categoryID);
            isDelete = ((preparedStatement.executeUpdate()) > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDelete;
    }

    public boolean updateCategory(String categoryID, String categoryName, String categoryDescription) {
        boolean isUpdate = false;
        String sql = "UPDATE tblCategory SET categoryName = ?, categoryDescription = ? WHERE categoryID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, categoryName);
            preparedStatement.setString(2, categoryDescription);
            preparedStatement.setString(3, categoryID);
            isUpdate = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }

    public String generateCategoryID() {
        String newCategoryID = "C";
        String sql = "SELECT categoryID FROM tblCategory";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Integer> listIndex = new ArrayList<>();
            int index = 1;
            while (resultSet.next()) {
                String existIndex = resultSet.getString(1);
                int i = Integer.parseInt(existIndex.substring(Math.max(0, existIndex.length() - 2)));
                listIndex.add(i);
            }
            for (int i : listIndex) {
                if (i >= index) {
                    index = i + 1;
                }
            }
            if (index < 10) {
                newCategoryID += "0";
                newCategoryID += index;
            } else {
                newCategoryID += index;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TopicDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newCategoryID;
    }

    public List<Category> searchCategoryByName(String searchContent) {
        List<Category> listCategory = new ArrayList<>();
        String sql = "SELECT * FROM tblCategory WHERE categoryName LIKE '%" + searchContent + "%'";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryID(resultSet.getString(1));
                category.setCategoryName(resultSet.getString(2));
                category.setCategoryDescription(resultSet.getString(3));
                listCategory.add(category);
            }
            resultSet.close();
            statement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategory;
    }
    public boolean checkCategoryName(String categoryName)
    {
        boolean isValidate = true;
        String sql = "SELECT categoryName FROM tblCategory";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                if ((resultSet.getString(1)).equals(categoryName))
                {
                    isValidate = false;
                    break;
                }
            }
            resultSet.close();
            statement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isValidate;
    }
}
