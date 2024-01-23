package com.example.hoodstudio_carpentryapp;

public class UserInformation {
    public String userName;
    public String userPhone;

    public UserInformation() {
    }

    public UserInformation(String userName, String userPhone) {
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
