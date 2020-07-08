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
