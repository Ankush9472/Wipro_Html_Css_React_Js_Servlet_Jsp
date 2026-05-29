package com.company.controller;

public class User {

    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public User(String name) {
        super();
        this.userName = name;
    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "User [userName=" + userName + "]";
    }
}