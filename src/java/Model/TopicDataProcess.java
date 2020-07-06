/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Topic;
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
public class TopicDataProcess {

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

    public List<Topic> getData() {
        List<Topic> topicList = new ArrayList<Topic>();
        String sql = "SELECT * FROM tblTopic";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Topic topic = new Topic();
                topic.setTopicID(resultSet.getString(1));
                topic.setTopicName(resultSet.getString(2));
                topic.setTopicDescription(resultSet.getString(3));
                topicList.add(topic);
            }
            resultSet.close();
            statement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TopicDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return topicList;
    }

    public Topic getDataByID(String topicID) {
        Topic topic = new Topic();
        String sql = "SELECT * FROM tblTopic WHERE topicID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, topicID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                topic.setTopicID(resultSet.getString(1));
                topic.setTopicName(resultSet.getString(2));
                topic.setTopicDescription(resultSet.getString(3));
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TopicDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return topic;
    }
    

    public List<Trainer> getTrainerInTopic(String topicID) {
        List<Trainer> trainerList = new ArrayList<Trainer>();
        String sql = "SELECT * FROM tblTrainer WHERE topicID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, topicID);
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
        } catch (SQLException ex) {
            Logger.getLogger(TopicDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainerList;
    }

    public boolean addTopic(String topicID, String topicName, String topicDescription) {
        boolean isInsert = false;
        String sql = "INSERT INTO tblTopic VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, topicID);
            preparedStatement.setString(2, topicName);
            preparedStatement.setString(3, topicDescription);
            isInsert = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TopicDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isInsert;
    }

    public boolean updateTopic(String topicID, String topicName, String topicDescription) {
        boolean isUpdate = false;
        String sql = "UPDATE tblTopic SET topicName = ?, topicDescription = ? WHERE topicID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, topicName);
            preparedStatement.setString(2, topicDescription);
            preparedStatement.setString(3, topicID);
            isUpdate = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TopicDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }

    public boolean deleteTopic(String topicID) {
        boolean isDelete = false;
        String sql = "DELETE tblTopic WHERE topicID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, topicID);
            isDelete = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TopicDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDelete;
    }

    public String generateTopicID() {
        String newTopicID = "T";
        String sql = "SELECT topicID FROM tblTopic";
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
                newTopicID += "0";
                newTopicID += index;
            } else {
                newTopicID += index;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TopicDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newTopicID;
    }
    public List<Topic> getDataByName(String searchContent)
    {
        List<Topic> listTopic = new ArrayList<>();
        String sql = "SELECT * FROM tblTopic WHERE topicName LIKE '%" + searchContent + "%'";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Topic topic = new Topic();
                topic.setTopicID(resultSet.getString(1));
                topic.setTopicName(resultSet.getString(2));
                topic.setTopicDescription(resultSet.getString(3));
                listTopic.add(topic);
            }
            resultSet.close();
            statement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(TopicDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTopic;
    }
    public static void main(String[] args) {
        TopicDataProcess t  = new TopicDataProcess();
        System.out.println(t.generateTopicID());
    }
//    public static void main(String[] args) {
//        TopicDataProcess t = new TopicDataProcess();
//        System.out.println(t.getTrainerInTopic("T01").size());
//    }
    }
