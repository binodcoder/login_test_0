package com.example.logindemo;

public class LoginModel {
    //declaration
    private int id;
    private String username;
    private int password;
    private boolean remember;

    //constructors
    public LoginModel(int id, String username, int password, boolean remember) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.remember = remember;
    }

    //print the data
    @Override
    public String toString() {
        return "LoginModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", remember=" + remember +
                '}';
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
