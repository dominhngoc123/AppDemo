/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ngoc Do Minh
 */
public class StaffDataProcess {
    public Connection getConnection()
    {
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
    public String getStaffName(String staffEmail)
    {
        String staffName = "";
        String sql = "SELECT staffName FROM tblStaff WHERE staffEmail = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, staffEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String fullName = resultSet.getString(1);
                if ((fullName.split("\\w+").length) > 1) {
                    staffName = fullName.substring(fullName.lastIndexOf(" ")+1);
                } else {
                    staffName = fullName;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return staffName;
    }
    public boolean checkLogin(String username, String password, int roleInt) {
        boolean isLogin = false;
        String sql = "SELECT * FROM tblStaff WHERE staffEmail = ? and _password = ? AND _role = ?";
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, roleInt);
            ResultSet resultSet = preparedStatement.executeQuery();
            isLogin = resultSet.next();
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isLogin;
    }
}
