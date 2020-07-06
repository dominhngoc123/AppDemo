/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Ngoc Do Minh
 */
public class Trainee {
    private String traineeID;
    private String _password;
    private String traineeName;
    private String traineeDoB;
    private String traineePhoneNumber;
    private String traineeEmail;
    private String traineeDetail;
    private String traineeAddress;

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    
    public String getTraineeAddress() {
        return traineeAddress;
    }

    public void setTraineeAddress(String traineeAddress) {
        this.traineeAddress = traineeAddress;
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
    
}
