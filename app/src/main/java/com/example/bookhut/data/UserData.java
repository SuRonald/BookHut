package com.example.bookhut.data;

public class UserData {
    int userID;
    String userEmail;
    String userName;
    String userPassword;
    int purchaseCount;

    public UserData(int userID, String userEmail, String userName, String userPassword, int purchaseCount) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.purchaseCount = purchaseCount;
    }

    public UserData(){

    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }
}
