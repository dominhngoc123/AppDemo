/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TrainingStaff;
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
public class TrainingStaffDataProcess {

    public Connection getConnection()
    {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ASMAD";
            String user = "sa";
            String password = "anhsaoxa11";
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
    
    public List<TrainingStaff> getData()
    {
        List<TrainingStaff> trainingStaffList = new ArrayList<>();
        String sql = "SELECT * FROM tblStaff WHERE _role = 2 ORDER BY staffName";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                TrainingStaff trainingStaff = new TrainingStaff();
                trainingStaff.setStaffID(resultSet.getString(1));
                trainingStaff.setPassword(resultSet.getString(2));
                trainingStaff.setStaffName(resultSet.getString(3));
                trainingStaff.setStaffDoB(resultSet.getString(4));
                trainingStaff.setStaffAddress(resultSet.getString(5));
                trainingStaff.setStaffPhonenumber(resultSet.getString(6));
                trainingStaff.setStaffEmail(resultSet.getString(7));
                trainingStaff.setRole(resultSet.getInt(8));
                trainingStaffList.add(trainingStaff);
            }
            resultSet.close();
            statement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainingStaffDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainingStaffList;
    }
    public TrainingStaff getDataByID(String staffID)
    {
        TrainingStaff trainingStaff = new TrainingStaff();
        String sql = "SELECT * FROM tblStaff WHERE staffID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, staffID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                trainingStaff.setStaffID(resultSet.getString(1));
                trainingStaff.setPassword(resultSet.getString(2));
                trainingStaff.setStaffName(resultSet.getString(3));
                trainingStaff.setStaffDoB(resultSet.getString(4));
                trainingStaff.setStaffAddress(resultSet.getString(5));
                trainingStaff.setStaffPhonenumber(resultSet.getString(6));
                trainingStaff.setStaffEmail(resultSet.getString(7));
                trainingStaff.setRole(resultSet.getInt(8));
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainingStaffDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainingStaff;
    }
    public TrainingStaff getDataByEmail(String staffEmail)
    {
        TrainingStaff trainingStaff = new TrainingStaff();
        String sql = "SELECT * FROM tblStaff WHERE staffEmail = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, staffEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                trainingStaff.setStaffID(resultSet.getString(1));
                trainingStaff.setPassword(resultSet.getString(2));
                trainingStaff.setStaffName(resultSet.getString(3));
                trainingStaff.setStaffDoB(resultSet.getString(4));
                trainingStaff.setStaffAddress(resultSet.getString(5));
                trainingStaff.setStaffPhonenumber(resultSet.getString(6));
                trainingStaff.setStaffEmail(resultSet.getString(7));
                trainingStaff.setRole(resultSet.getInt(8));
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainingStaffDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainingStaff;
    }
    public List<TrainingStaff> searchStaffByName(String searchString)
    {
        List<TrainingStaff> trainingStaffSearchList = new ArrayList<>();
        String sql = "SELECT * FROM tblStaff WHERE staffName LIKE '%" + searchString + "%' AND _role = 2";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                TrainingStaff trainingStaff = new TrainingStaff();
                trainingStaff.setStaffID(resultSet.getString(1));
                trainingStaff.setPassword(resultSet.getString(2));
                trainingStaff.setStaffName(resultSet.getString(3));
                trainingStaff.setStaffDoB(resultSet.getString(4));
                trainingStaff.setStaffAddress(resultSet.getString(5));
                trainingStaff.setStaffPhonenumber(resultSet.getString(6));
                trainingStaff.setStaffEmail(resultSet.getString(7));
                trainingStaff.setRole(resultSet.getInt(8));
                trainingStaffSearchList.add(trainingStaff);
            }
            resultSet.close();
            statement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainingStaffDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainingStaffSearchList;
    }
    public boolean addTrainingStaff(String staffID, String password, String staffName, String staffDoB, String staffAddress, String staffPhonenumber, String staffEmail)
    {
        boolean isInsert = false;
        String sql = "INSERT INTO tblStaff VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, staffID);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, staffName);
            preparedStatement.setString(4, staffDoB);
            preparedStatement.setString(5, staffAddress);
            preparedStatement.setString(6, staffPhonenumber);
            preparedStatement.setString(7, staffEmail);
            preparedStatement.setInt(8, 2);
            int i = preparedStatement.executeUpdate();
            isInsert = i > 0;
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainingStaffDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isInsert;
    }
    public boolean updateTrainingStaff(String staffID, String password, String staffName, String staffDoB, String staffAddress, String staffPhonenumber, String staffEmail)
    {
        boolean isUpdate = false;
        String sql = "UPDATE tblStaff SET _password = ?, staffName = ?, staffDoB = ?, staffAddress = ?, staffPhoneNumber = ?, staffEmail = ? WHERE staffID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(7, staffID);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, staffName);
            preparedStatement.setString(3, staffDoB);
            preparedStatement.setString(4, staffAddress);
            preparedStatement.setString(5, staffPhonenumber);
            preparedStatement.setString(6, staffEmail);
            isUpdate = (preparedStatement.executeUpdate()) > 0;
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainingStaffDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }
    public boolean deleteTrainingStaff(String staffID)
    {
        boolean isDelete = false;
        String sql = "DELETE tblStaff WHERE staffID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, staffID);
            isDelete = (preparedStatement.executeUpdate()) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TrainingStaffDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDelete;
    }
}
