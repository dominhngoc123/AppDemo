/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Trainee;
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
public class TraineeDataProcess {

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

    public List<Trainee> getTraineeInCourse(String courseID) {
        List<Trainee> traineeList = new ArrayList<Trainee>();
        String sql = "SELECT t.* FROM tblTrainee as t INNER JOIN tblCourseRegister as c ON t.traineeID = c.TraineeID and c.courseID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, courseID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Trainee trainee = new Trainee();
                trainee.setTraineeID(resultSet.getString(1));
                trainee.setPassword(resultSet.getString(8));
                trainee.setTraineeName(resultSet.getString(2));
                trainee.setTraineeDoB(resultSet.getString(3));
                trainee.setTraineeAddress(resultSet.getString(4));
                trainee.setTraineePhoneNumber(resultSet.getString(5));
                trainee.setTraineeEmail(resultSet.getString(6));
                trainee.setTraineeDetail(resultSet.getString(7));
                traineeList.add(trainee);
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return traineeList;
    }
    public static void main(String[] args) {
        TraineeDataProcess t = new TraineeDataProcess();
        Trainee tr = t.getDataByID("Demo123123");
        System.out.println(tr.getPassword());
    }

    public List<Trainee> searchTraineeByName(String searchContent) {
        List<Trainee> traineeList = new ArrayList<Trainee>();
        String sql = "SELECT * FROM tblTrainee WHERE traineeName LIKE '%" + searchContent + "%'";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Trainee trainee = new Trainee();
                trainee.setTraineeID(resultSet.getString(1));
                trainee.setTraineeName(resultSet.getString(2));
                trainee.setTraineeDoB(resultSet.getString(3));
                trainee.setTraineeAddress(resultSet.getString(4));
                trainee.setTraineePhoneNumber(resultSet.getString(5));
                trainee.setTraineeEmail(resultSet.getString(6));
                trainee.setTraineeDetail(resultSet.getString(7));
                traineeList.add(trainee);
            }
            resultSet.close();
            statement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TraineeDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return traineeList;
    }

    public Trainee getDataByID(String traineeID) {
        Trainee trainee = new Trainee();
        String sql = "SELECT * FROM tblTrainee WHERE traineeID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, traineeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trainee.setTraineeID(traineeID);
                trainee.setTraineeName(resultSet.getString(2));
                trainee.setTraineeDoB(resultSet.getString(3));
                trainee.setTraineeAddress(resultSet.getString(4));
                trainee.setTraineePhoneNumber(resultSet.getString(5));
                trainee.setTraineeEmail(resultSet.getString(6));
                trainee.setTraineeDetail(resultSet.getString(7));
                trainee.setPassword(resultSet.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TraineeDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainee;
    }

    public List<Trainee> getData() {
        List<Trainee> traineeList = new ArrayList<Trainee>();
        String sql = "SELECT * FROM tblTrainee";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Trainee trainee = new Trainee();
                trainee.setTraineeID(resultSet.getString(1));
                trainee.setTraineeName(resultSet.getString(2));
                trainee.setTraineeDoB(resultSet.getString(3));
                trainee.setTraineeAddress(resultSet.getString(4));
                trainee.setTraineePhoneNumber(resultSet.getString(5));
                trainee.setTraineeEmail(resultSet.getString(6));
                trainee.setTraineeDetail(resultSet.getString(7));
                traineeList.add(trainee);
            }
            resultSet.close();
            statement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return traineeList;
    }

    public List<Trainee> getTraineeOutCourse(String courseID) {
        List<Trainee> traineeList = new ArrayList<Trainee>();
        String sql = "SELECT * FROM tblTrainee WHERE traineeID NOT IN (SELECT t.traineeID FROM tblTrainee as t INNER JOIN tblCourseRegister as c ON t.traineeID = c.TraineeID and c.courseID = ?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, courseID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Trainee trainee = new Trainee();
                trainee.setTraineeID(resultSet.getString(1));
                trainee.setTraineeName(resultSet.getString(2));
                trainee.setTraineeDoB(resultSet.getString(3));
                trainee.setTraineeAddress(resultSet.getString(4));
                trainee.setTraineePhoneNumber(resultSet.getString(5));
                trainee.setTraineeEmail(resultSet.getString(6));
                trainee.setTraineeDetail(resultSet.getString(7));
                traineeList.add(trainee);
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return traineeList;
    }

    public boolean assignTraineeToCourse(String courseID, String traineeID) {
        int isInsert = 0;
        String sql = "INSERT INTO tblCourseRegister VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, courseID);
            preparedStatement.setString(2, traineeID);
            isInsert = preparedStatement.executeUpdate();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TraineeDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (isInsert > 0);
    }

    public boolean removeTraineeFromCourse(String courseID, String traineeID) {
        int isDelete = 0;
        String sql = "DELETE tblCourseRegister WHERE traineeID = ? AND courseID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, traineeID);
            preparedStatement.setString(2, courseID);
            isDelete = preparedStatement.executeUpdate();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TraineeDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (isDelete > 0);
    }

    public boolean addTrainee(String traineeID, String password, String traineeName, String traineeDoB, String traineeAddress, String traineePhoneNumber, String traineeEmail, String traineeDetail) {
        int check = 0;
        String sql = "INSERT INTO tblTrainee VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, traineeID);
            preparedStatement.setString(2, traineeName);
            preparedStatement.setString(3, traineeDoB);
            preparedStatement.setString(4, traineeAddress);
            preparedStatement.setString(5, traineePhoneNumber);
            preparedStatement.setString(6, traineeEmail);
            preparedStatement.setString(7, traineeDetail);
            preparedStatement.setString(8, password);
            check = preparedStatement.executeUpdate();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TraineeDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check > 0);
    }

    public List<String> checkDelete(String traineeID) {
        List<String> listCourse = new ArrayList<>();
        String sqlCheck = "SELECT * FROM tblCourseRegister WHERE traineeID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlCheck);
            preparedStatement.setString(1, traineeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listCourse.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TraineeDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCourse;
    }

    public String deleteTrainee(String traineeID) {
        String msg = "";
        List<String> listCourse = checkDelete(traineeID);
        if (listCourse.size() == 0) {
            String sql = "DELETE tblTrainee WHERE traineeID = ?";
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                preparedStatement.setString(1, traineeID);
                int check = preparedStatement.executeUpdate();
                if (check > 0)
                {
                    msg += "Successfully delete trainee with ID: " + traineeID;
                }
                preparedStatement.close();
                getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(TraineeDataProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            msg += "Cannot delete trainee with ID: " + traineeID + " ";
            msg += "because this trainee belong to course(s): ";
            for (String str: listCourse)
            {
                msg += "|" + str + " ";
            }
        }
        return msg;
    }

    public List<Trainee> searchTraineeInList(String searchContent, List<Trainee> inputList) {
        List<Trainee> listTrainee = new ArrayList<>();
        for (Trainee trainee : inputList) {
            if ((trainee.getTraineeName()).contains(searchContent)) {
                listTrainee.add(trainee);
            }
        }
        return listTrainee;
    }

    public boolean updateTrainee(String traineeID, String password, String traineeName, String traineeDoB, String traineeAddress, String traineePhoneNumber, String traineeEmail, String traineeDetail) {
        boolean isUpdate = false;
        String sql = "UPDATE tblTrainee SET _password = ?, traineeName = ?, traineeDoB = ?, traineeAddress = ?, traineePhoneNumber = ?, traineeEmail = ?, traineeDetail = ? WHERE traineeID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, traineeName);
            preparedStatement.setString(3, traineeDoB);
            preparedStatement.setString(4, traineeAddress);
            preparedStatement.setString(5, traineePhoneNumber);
            preparedStatement.setString(6, traineeEmail);
            preparedStatement.setString(7, traineeDetail);
            preparedStatement.setString(8, traineeID);
            int i = preparedStatement.executeUpdate();
            isUpdate = i > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TraineeDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }
}
