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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ngoc Do Minh
 */
public class AccountDataProcess {

    public Connection getConnection() {
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

    public boolean checkLogin(String username, String password, String roleStr, int roleInt) {
        boolean isLogin = false;
        String sql = "SELECT * FROM tbl" + roleStr + " WHERE " + roleStr.toLowerCase() + "Email = ? and _password = ? AND _role = ?";
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

    public boolean checkUser(String email, int role) {
        boolean isExist = false;
        String sql = "";
        if (role == 3) {
            sql = "SELECT * FROM tblTrainer WHERE trainerEmail = ?";
        } else {
            sql = "SELECT * FROM tblStaff WHERE staffEmail = ?";
        }
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            isExist = resultSet.next();
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }
    public static void main(String[] args) {
        AccountDataProcess a = new AccountDataProcess();
        System.out.println(a.getConnection());
    }

    public boolean updatePassword(String email, int role) {
        boolean isUpdate = false;
        String sql = "";
        if (role == 3) {
            sql = "UPDATE tblTrainer SET _password = ? WHERE AND trainerEmail = ?";
        } else {
            sql = "UPDATE tblStaff SET _password = ? WHERE staffEmail = ?";
        }
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            String passwordChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder pwd = new StringBuilder();
            Random random = new Random();
            while (pwd.length() < 6) {
                int index = (int) (random.nextFloat() * passwordChar.length());
                pwd.append(passwordChar.charAt(index));
            }
            String newPassword = pwd.toString();
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            isUpdate = (preparedStatement.executeUpdate() > 0);
            SendEmail sendEmail = new SendEmail();
            sendEmail.SendEmail(email, newPassword);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }
//    public static void main(String[] args) {
//        String passwordChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//            StringBuilder pwd = new StringBuilder();
//            Random random = new Random();
//            while (pwd.length() < 6)
//            {
//                int index = (int) (random.nextFloat() * passwordChar.length());
//                pwd.append(passwordChar.charAt(index));
//            }
//            String newPassword = pwd.toString();
//            System.out.println(newPassword);
//    }

    public boolean userUpdateProfile(String userID, String userPassword, String userDoB, String userPhoneNumber, String userAddress, String userCertificate, String userType, int userRole) {
        boolean isUpdate = false;
        if (userRole == 3) {
            String sql = "UPDATE tblTrainer SET _password = ?, trainerDoB = ?, trainerAddress = ?, trainerPhoneNumber = ?, trainerCertificate = ?, trainerType = ? WHERE trainerID = ?";
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                preparedStatement.setString(1, userPassword);
                preparedStatement.setString(2, userDoB);
                preparedStatement.setString(3, userAddress);
                preparedStatement.setString(4, userPhoneNumber);
                preparedStatement.setString(5, userCertificate);
                preparedStatement.setString(6, userType);
                preparedStatement.setString(7, userID);
                int i = preparedStatement.executeUpdate();
                preparedStatement.close();
                getConnection().close();
                isUpdate = (i > 0);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDataProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String sql = "UPDATE tblStaff SET _password = ?, staffDoB = ?, staffAddress = ?, staffPhoneNumber = ? WHERE staffID = ?";
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                preparedStatement.setString(1, userPassword);
                preparedStatement.setString(2, userDoB);
                preparedStatement.setString(3, userAddress);
                preparedStatement.setString(4, userPhoneNumber);
                preparedStatement.setString(5, userID);
                int i = preparedStatement.executeUpdate();
                preparedStatement.close();
                getConnection().close();
                isUpdate = (i > 0);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDataProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isUpdate;
    }
}
