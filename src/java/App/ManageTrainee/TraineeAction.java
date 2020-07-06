/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.ManageTrainee;

import Entity.Trainee;
import Model.TraineeDataProcess;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ngoc Do Minh
 */
public class TraineeAction extends ActionSupport {

    private List<Trainee> listTrainee = new ArrayList<>();
    private List<Trainee> searchList = new ArrayList<>();
    private String courseID;
    private String traineeID;
    private String traineePassword;
    private String traineeName;
    private String traineeDoB;
    private String traineeAddress;
    private String traineePhoneNumber;
    private String traineeEmail;
    private String traineeDetail;
    private String action;
    private String sltChoice;
    private String searchContent;
    private String msg;
    private String url;
    private Trainee trainee;

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public String getTraineePassword() {
        return traineePassword;
    }

    public void setTraineePassword(String traineePassword) {
        this.traineePassword = traineePassword;
    }

    
    public List<Trainee> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Trainee> searchList) {
        this.searchList = searchList;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSltChoice() {
        return sltChoice;
    }

    public void setSltChoice(String sltChoice) {
        this.sltChoice = sltChoice;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTraineeID() {
        return traineeID;
    }

    public void setTraineeID(String traineeID) {
        this.traineeID = traineeID;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public String getTraineeDoB() {
        return traineeDoB;
    }

    public void setTraineeDoB(String traineeDoB) {
        this.traineeDoB = traineeDoB;
    }

    public String getTraineeAddress() {
        return traineeAddress;
    }

    public void setTraineeAddress(String traineeAddress) {
        this.traineeAddress = traineeAddress;
    }

    public String getTraineePhoneNumber() {
        return traineePhoneNumber;
    }

    public void setTraineePhoneNumber(String traineePhoneNumber) {
        this.traineePhoneNumber = traineePhoneNumber;
    }

    public String getTraineeEmail() {
        return traineeEmail;
    }

    public void setTraineeEmail(String traineeEmail) {
        this.traineeEmail = traineeEmail;
    }

    public String getTraineeDetail() {
        return traineeDetail;
    }

    public void setTraineeDetail(String traineeDetail) {
        this.traineeDetail = traineeDetail;
    }

    public List<Trainee> getListTrainee() {
        return listTrainee;
    }

    public void setListTrainee(List<Trainee> listTrainee) {
        this.listTrainee = listTrainee;
    }

    public TraineeAction() {
    }

    public String getData() throws Exception {
        TraineeDataProcess traineeDataProcess = new TraineeDataProcess();
        listTrainee = traineeDataProcess.getData();
        return "TRAINEELIST";
    }

    public String getDataByID() {
        TraineeDataProcess traineeDataProcess = new TraineeDataProcess();
        trainee = traineeDataProcess.getDataByID(traineeID);
        if (("Update").equals(action))
        {
            return "DETAILTRAINEEFORUPDATE";
        }
        else
        {
            return "DETAILTRAINEEFORVIEW";
        }
    }

    public String addTrainee() {
        TraineeDataProcess traineeDataProcess = new TraineeDataProcess();
        if (traineeDataProcess.addTrainee(traineeID, traineePassword, traineeName, traineeDoB, traineeAddress, traineePhoneNumber, traineeEmail, traineeDetail)) {
            msg = "Add new trainee success";
            return "ADDSUCCESS";
        }
        msg = "Cannot add new trainee";
        return "ADDFAILED";
    }

    public String deleteTrainee() {
        TraineeDataProcess traineeDataProcess = new TraineeDataProcess();
        msg = traineeDataProcess.deleteTrainee(traineeID);
        return "DELETEFAILED";
    }
    public String updateTrainee()
    {
        TraineeDataProcess traineeDataProcess = new TraineeDataProcess();
        if (traineeDataProcess.updateTrainee(traineeID, traineePassword, traineeName, traineeDoB, traineeAddress, traineePhoneNumber, traineeEmail, traineeDetail))
        {
            msg = "Update trainee success";
            return "UPDATETRAINEESUCCESS";
        }
        msg = "Cannot update trainee";
        return "UPDATETRAINEEFAILED";
    }

    public String getTraineeInCourse() {
        TraineeDataProcess traineeDataProcess = new TraineeDataProcess();
        listTrainee = traineeDataProcess.getTraineeInCourse(courseID);
        return "LISTTRAINEEINCOURSE";
    }

    public String getTraineeOutCourse() {
        TraineeDataProcess traineeDataProcess = new TraineeDataProcess();
        listTrainee = traineeDataProcess.getTraineeOutCourse(courseID);
        return "LISTTRAINEEOUTCOURSE";
    }

    public String searchTraineeInCourse() {
        TraineeDataProcess traineeDataProcess = new TraineeDataProcess();
        listTrainee = traineeDataProcess.getTraineeInCourse(courseID);
        for (Trainee trainee : listTrainee) {
            if (trainee.getTraineeName().contains(searchContent)) {
                searchList.add(trainee);
            }
        }
        return "SEARCHINLIST";
    }

    public String searchTraineeOutCourse() {
        TraineeDataProcess traineeDataProcess = new TraineeDataProcess();
        listTrainee = traineeDataProcess.getTraineeOutCourse(courseID);
        for (Trainee trainee : listTrainee) {
            if (trainee.getTraineeName().contains(searchContent)) {
                searchList.add(trainee);
            }
        }
        return "SEARCHOUTLIST";
    }
}
