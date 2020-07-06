/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Course;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ngoc Do Minh
 */
public class CourseDataProcess {

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

    public List<Course> getData() {
        List<Course> listCourse = new ArrayList<Course>();
        String sql = "SELECT * FROM tblCourse ORDER BY insertDate DESC";
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
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
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCourse;
    }

    public Course getDatabyID(String courseID) {
        Course course = new Course();
        String sql = "SELECT * FROM tblCourse WHERE courseID = ? ORDER BY insertDate DESC";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, courseID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                course.setCourseID(resultSet.getString(1));
                course.setCourseName(resultSet.getString(2));
                course.setCourseStartDate(resultSet.getString(3));
                course.setCourseEndDate(resultSet.getString(4));
                course.setCourseLocation(resultSet.getString(5));
                course.setCategoryID(resultSet.getString(6));
                course.setTrainerID(resultSet.getString(7));
                course.setCourseContent(resultSet.getString(8));
                course.setStatus(resultSet.getInt(9));
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;
    }

    public List<Course> searchCourseByName(String searchContent) {
        List<Course> listCourse = new ArrayList<Course>();
        String sql = "SELECT * FROM tblCourse WHERE courseName LIKE '%" + searchContent + "%' ORDER BY insertDate DESC";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
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
            statement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCourse;
    }

//    public static void main(String[] args) {
//        CourseDataProcess c = new CourseDataProcess();
//        System.out.println(c.searchCourseByName("1").size());
//    }
    public boolean updateCourse(String courseID, String courseName, String courseStartDate, String courseEndDate, String courseLocation, String categoryID, String trainerID, String courseContent) {
        boolean isUpdate = false;
        String sql = "UPDATE tblCourse SET courseName = ?, courseStartDate = ?, courseEndDate = ?, courseLocation = ?, categoryID = ?, trainerID = ?, courseContent = ? WHERE courseID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, courseName);
            preparedStatement.setString(2, courseStartDate);
            preparedStatement.setString(3, courseEndDate);
            preparedStatement.setString(4, courseLocation);
            preparedStatement.setString(5, categoryID);
            preparedStatement.setString(6, trainerID);
            preparedStatement.setString(7, courseContent);
            preparedStatement.setString(8, courseID);
            isUpdate = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }
    public boolean updateCourseStatus(String courseID)
    {
        boolean isUpdate = false;
        String sql = "UPDATE tblCourse SET _status = 0 WHERE courseID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, courseID);
            isUpdate = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }
    public boolean deleteCourse(String courseID) {
        boolean isDelete = false;
        String sql = "DELETE tblCourse WHERE courseID = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, courseID);
            isDelete = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDelete;
    }

    public boolean addCourse(String courseID, String courseName, String courseStartDate, String courseEndDate, String courseLocation, String categoryID, String trainerID, String courseContent) {
        int check = 0;
        String sql = "INSERT INTO tblCourse VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try {
            Date date = new Date();
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, courseID);
            preparedStatement.setString(2, courseName);
            preparedStatement.setString(3, courseStartDate);
            preparedStatement.setString(4, courseEndDate);
            preparedStatement.setString(5, courseLocation);
            preparedStatement.setString(6, categoryID);
            preparedStatement.setString(7, trainerID);
            preparedStatement.setString(8, courseContent);
            preparedStatement.setInt(9, 1);
            check = preparedStatement.executeUpdate();
            preparedStatement.close();
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check > 0);
    }
//    public static void main(String[] args) {
//        CourseDataProcess c = new CourseDataProcess();
//        System.out.println(c.getData().size());
//    }
    public boolean checkValidate(String courseName) {
        boolean isValidate = false;
        String sql = "SELECT * FROM tblCourse WHERE courseName = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            isValidate = resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isValidate;
    }

    public String generateCourseID(String topicName) {
        String newCourseID = "";
        if (topicName != null) {
            newCourseID = topicName.substring(0, 2).toUpperCase() + "-";
            String sql = "SELECT courseID FROM tblCourse WHERE courseID LIKE '%" + newCourseID + "%'";
            try {
                int index = 1;
                List<Integer> listCourseIndex = new ArrayList<>();
                Statement statement = getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String courseID = resultSet.getString(1);
                    index = Integer.parseInt(courseID.substring(Math.max(0, courseID.length() - 5)));
                    listCourseIndex.add(index);
                }
                for (int i : listCourseIndex) {
                    if (i >= index) {
                        index = i + 1;
                    }
                }
                if (index < 10) {
                    newCourseID += "0000";
                    newCourseID += index;
                } else if (index < 100) {
                    newCourseID += "000";
                    newCourseID += index;
                } else if (index < 1000) {
                    newCourseID += "00";
                    newCourseID += index;
                } else if (index < 10000) {
                    newCourseID += "0";
                    newCourseID += index;
                } else if (index < 100000) {
                    newCourseID += index;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CourseDataProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return newCourseID;
    }
    public static void main(String[] args) {
        CourseDataProcess c = new CourseDataProcess();
        System.out.println(c.generateCourseID("JAVA Core"));
    }
}
