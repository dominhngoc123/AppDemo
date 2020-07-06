/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Course;
import Entity.Trainer;
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
public class TrainerDataProcess {

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

    public boolean checkLogin(String username, String password) {
        boolean isLogin = false;
        String sql = "SELECT * FROM tblTrainer WHERE trainerEmail = ? and _password = ?";
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
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
    public String getTrainerName(String trainerEmail)
    {
        String trainerName = "";
        String sql = "SELECT trainerName FROM tblTrainer WHERE trainerEmail = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, trainerEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String fullName = resultSet.getString(1);
                if ((fullName.split("\\w+").length) > 1) {
                    trainerName = fullName.substring(fullName.lastIndexOf(" ")+1);
                } else {
                    trainerName = fullName;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainerName;
    }
//    public List<Topic> getTopicOfTrainer(String trainerID)
//    {
//        List<Topic> listTopic = new ArrayList<Topic>();
//        String sql = ""
//        return listTopic;
//    }
    public List<Trainer> getData() {
        List<Trainer> trainerList = new ArrayList<Trainer>();
        String sql = "SELECT * FROM tblTrainer";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Trainer trainer = new Trainer();
                trainer.setTrainerID(resultSet.getString(1));
                trainer.setPassword(resultSet.getString(2));
                trainer.setTrainerName(resultSet.getString(3));
                trainer.setTrainerDoB(resultSet.getString(4));
                trainer.setTrainerAddress(resultSet.getString(5));
                trainer.setTrainerPhoneNumber(resultSet.getString(6));
                trainer.setTrainerEmail(resultSet.getString(7));
                trainer.setTrainerCertificate(resultSet.getString(8));
                trainer.setTrainerType(resultSet.getString(9));
                trainer.setTopicID(resultSet.getString(10));
                trainerList.add(trainer);
            }
            resultSet.close();
            statement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainerList;
    }
    public Trainer getDataByEmail(String trainerEmail) {
        Trainer trainer = new Trainer();
        String sql = "SELECT * FROM tblTrainer WHERE trainerEmail = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, trainerEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trainer.setTrainerID(resultSet.getString(1));
                trainer.setPassword(resultSet.getString(2));
                trainer.setTrainerName(resultSet.getString(3));
                trainer.setTrainerDoB(resultSet.getString(4));
                trainer.setTrainerAddress(resultSet.getString(5));
                trainer.setTrainerPhoneNumber(resultSet.getString(6));
                trainer.setTrainerEmail(resultSet.getString(7));
                trainer.setTrainerCertificate(resultSet.getString(8));
                trainer.setTrainerType(resultSet.getString(9));
                trainer.setTopicID(resultSet.getString(10));
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainer;
    }

    public Trainer getDataByID(String trainerID) {
        Trainer trainer = new Trainer();
        String sql = "SELECT * FROM tblTrainer WHERE trainerID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, trainerID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trainer.setTrainerID(resultSet.getString(1));
                trainer.setPassword(resultSet.getString(2));
                trainer.setTrainerName(resultSet.getString(3));
                trainer.setTrainerDoB(resultSet.getString(4));
                trainer.setTrainerAddress(resultSet.getString(5));
                trainer.setTrainerPhoneNumber(resultSet.getString(6));
                trainer.setTrainerEmail(resultSet.getString(7));
                trainer.setTrainerCertificate(resultSet.getString(8));
                trainer.setTrainerType(resultSet.getString(9));
                trainer.setTopicID(resultSet.getString(10));
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainer;
    }

    public List<Trainer> getTrainerByTopicName(String topicName) {
        List<Trainer> trainerList = new ArrayList<Trainer>();
        String sql = "SELECT * FROM tblTrainer WHERE topicName = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, topicName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Trainer trainer = new Trainer();
                trainer.setTrainerID(resultSet.getString(1));
                trainer.setPassword(resultSet.getString(2));
                trainer.setTrainerName(resultSet.getString(3));
                trainer.setTrainerDoB(resultSet.getString(4));
                trainer.setTrainerAddress(resultSet.getString(5));
                trainer.setTrainerPhoneNumber(resultSet.getString(6));
                trainer.setTrainerEmail(resultSet.getString(7));
                trainer.setTrainerCertificate(resultSet.getString(8));
                trainer.setTrainerType(resultSet.getString(9));
                trainer.setTopicID(resultSet.getString(10));
                trainerList.add(trainer);
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainerList;
    }
    public boolean addTrainer(String trainerID, String password, String trainerName, String trainerDoB, String trainerAddress, String trainerPhoneNumber, String trainerEmail, String trainerCertificate, String trainerType, String topicID)
    {
        boolean isInsert = false;
        String sql = "INSERT INTO tblTrainer VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, trainerID);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, trainerName);
            preparedStatement.setString(4, trainerDoB);
            preparedStatement.setString(5, trainerAddress);
            preparedStatement.setString(6, trainerPhoneNumber);
            preparedStatement.setString(7, trainerEmail);
            preparedStatement.setString(8, trainerCertificate);
            preparedStatement.setString(9, trainerType);
            preparedStatement.setString(10, topicID);
            int i = preparedStatement.executeUpdate();
            isInsert = (i > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isInsert;
    }
    public boolean deleteTrainer(String trainerID)
    {
        boolean isDelete = false;
        String sql = "DELETE tblTrainer WHERE trainerID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, trainerID);
            int i = preparedStatement.executeUpdate();
            isDelete = (i > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDelete;
    }
    public static void main(String[] args) {
        TrainerDataProcess t = new TrainerDataProcess();
        System.out.println(t.getTrainerCourse("Trainer00").size());
    }
    public List<Course> getTrainerCourse(String trainerID)
    {
        List<Course> listCourse = new ArrayList<>();
        String sql = "SELECT * FROM tblCourse WHERE trainerID = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, trainerID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Course course = new Course();
                course.setCourseID(resultSet.getString(1));
                course.setCourseName(resultSet.getString(2));
                course.setCourseStartDate(resultSet.getString(3));
                course.setCourseEndDate(resultSet.getString(4));
                course.setCourseLocation(resultSet.getString(5));
                course.setCategoryID(resultSet.getString(6));
                course.setTrainerID(resultSet.getString(7));
                course.setCourseContent(resultSet.getString(8));
                course.setStatus(resultSet.getInt(9));
                listCourse.add(course);
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return listCourse;
    }
    public boolean updateTrainer(String trainerID, String password, String trainerName, String trainerDoB, String trainerAddress, String trainerPhoneNumber, String trainerEmail, String trainerCertificate, String trainerType, String topicID)
    {
        boolean isUpdate = false;
        String sql = "UPDATE tblTrainer SET _password = ?, trainerName = ?, trainerDoB = ?, trainerAddress = ?, trainerPhoneNumber = ?, trainerEmail = ?, trainerCertificate = ?, trainerType = ?, topicID = ? WHERE trainerID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, trainerName);
            preparedStatement.setString(3, trainerDoB);
            preparedStatement.setString(4, trainerAddress);
            preparedStatement.setString(5, trainerPhoneNumber);
            preparedStatement.setString(6, trainerEmail);
            preparedStatement.setString(7, trainerCertificate);
            preparedStatement.setString(8, trainerType);
            preparedStatement.setString(9, topicID);
            preparedStatement.setString(10, trainerID);
            isUpdate = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }
}
